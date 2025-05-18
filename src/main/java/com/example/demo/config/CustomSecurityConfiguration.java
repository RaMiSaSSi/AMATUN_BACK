package com.example.demo.config;

                        import com.example.demo.filters.JwtRequestFilter;
                        import com.example.demo.model.Role;
                        import com.example.demo.service.Auth.CustomUserDetailsService;
                        import org.springframework.context.annotation.Bean;
                        import org.springframework.context.annotation.Configuration;
                        import org.springframework.context.annotation.Primary;
                        import org.springframework.security.authentication.AuthenticationManager;
                        import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
                        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
                        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
                        import org.springframework.security.config.http.SessionCreationPolicy;
                        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
                        import org.springframework.security.crypto.password.PasswordEncoder;
                        import org.springframework.security.web.SecurityFilterChain;

                        @Configuration
                        @EnableWebSecurity
                        public class CustomSecurityConfiguration {

                            private final CustomUserDetailsService userDetailsService;
                            private final JwtRequestFilter authFilter;

                            public CustomSecurityConfiguration(CustomUserDetailsService userDetailsService, JwtRequestFilter authFilter) {
                                this.userDetailsService = userDetailsService;
                                this.authFilter = authFilter;
                            }

                            @Bean
                            @Primary
                            public SecurityFilterChain configure(HttpSecurity http) throws Exception {
                                return http
                                        .csrf(csrf -> csrf.disable())
                                        .authorizeHttpRequests(auth -> auth
                                                .anyRequest().permitAll()) // Allow other requests
                                        .sessionManagement(session ->
                                                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                        .build();
                            }

                            @Bean
                            public PasswordEncoder passwordEncoder() {
                                return new BCryptPasswordEncoder();
                            }

                            @Bean
                            public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
                                AuthenticationManagerBuilder authenticationManagerBuilder =
                                        http.getSharedObject(AuthenticationManagerBuilder.class);
                                authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
                                return authenticationManagerBuilder.build();
                            }
                        }