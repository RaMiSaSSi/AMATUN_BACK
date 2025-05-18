package com.example.demo.filters;

    import com.example.demo.utils.JwtUtil;
    import jakarta.servlet.FilterChain;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.stereotype.Component;
    import org.springframework.web.filter.OncePerRequestFilter;

    import java.io.IOException;
    import java.util.stream.Collectors;

    @Component
    @RequiredArgsConstructor
    public class JwtRequestFilter extends OncePerRequestFilter {

        @Autowired
        private UserDetailsService userDetailsService;

        @Autowired
        private JwtUtil jwtUtil;

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {

            String authHeader = request.getHeader("Authorization");
            String username = null;
            String jwtToken = null;

            // Extract JWT token from Authorization header
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                jwtToken = authHeader.substring(7);
                try {
                    username = jwtUtil.extractUsername(jwtToken);
                } catch (Exception e) {
                    System.out.println("Error extracting username: " + e.getMessage());
                }
            }

            // Validate token and set authentication
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (jwtUtil.validateToken(jwtToken, userDetails.getUsername())) {
                    // Add ROLE_ prefix dynamically to authorities
                    var authorities = userDetails.getAuthorities().stream()
                            .map(auth -> new SimpleGrantedAuthority("ROLE_" + auth.getAuthority()))
                            .collect(Collectors.toList());

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, authorities);
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);
        }
    }