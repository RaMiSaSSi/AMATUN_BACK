// service/impl/AdresseServiceImpl.java
        package com.example.demo.service.Admin;

        import com.example.demo.model.Adresse;
        import com.example.demo.repository.AdresseRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;

        @Service
        public class AdresseServiceImpl implements AdresseService {

            @Autowired
            private AdresseRepository adresseRepository;

            @Override
            public Adresse getAdresseById(long id) {
                return adresseRepository.findById(id).orElse(null);
            }

            @Override
            public Adresse saveAdresse(Adresse adresse) {
                return adresseRepository.save(adresse);
            }

            @Override
            public void deleteAdresse(long id) {
                adresseRepository.deleteById(id);
            }

            @Override
            public Adresse updateAdresse(long id, Adresse adresse) {
                Adresse existingAdresse = adresseRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Adresse not found"));
                existingAdresse.setVille(adresse.getVille());
                existingAdresse.setCodePostal(adresse.getCodePostal());
                existingAdresse.setPays(adresse.getPays());
                existingAdresse.setRue(adresse.getRue());
                existingAdresse.setLongitude(adresse.getLongitude());
                existingAdresse.setLatitude(adresse.getLatitude());
                return adresseRepository.save(existingAdresse);
            }

            @Override
            public List<Adresse> getAllAdresses() {
                return adresseRepository.findAll();
            }
        }