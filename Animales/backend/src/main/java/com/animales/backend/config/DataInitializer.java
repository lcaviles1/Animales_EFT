package com.animales.backend.config;

import com.animales.backend.entity.Mascota;
import com.animales.backend.repository.MascotaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(MascotaRepository mascotaRepository) {
        return args -> {
            if (mascotaRepository.count() == 0) {
                mascotaRepository.save(new Mascota(
                        null, "Luna", "Perro", "Mestiza", 2,
                        "Santiago", "Hembra",
                        "https://placedog.net/500/300?id=1", "Disponible"
                ));

                mascotaRepository.save(new Mascota(
                        null, "Simba", "Gato", "Naranja", 3,
                        "Providencia", "Macho",
                        "https://placekitten.com/500/300", "Disponible"
                ));

                mascotaRepository.save(new Mascota(
                        null, "Nube", "Perro", "Poodle", 1,
                        "Maipu", "Hembra",
                        "https://placedog.net/500/300?id=2", "Disponible"
                ));
            }
        };
    }
}