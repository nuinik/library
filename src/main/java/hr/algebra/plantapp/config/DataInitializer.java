package hr.algebra.plantapp.config;

import hr.algebra.plantapp.entity.AppUser;
import hr.algebra.plantapp.entity.Plant;
import hr.algebra.plantapp.entity.PlantType;
import hr.algebra.plantapp.entity.Role;
import hr.algebra.plantapp.repository.PlantRepository;
import hr.algebra.plantapp.repository.PlantTypeRepository;
import hr.algebra.plantapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initializeData(UserRepository userRepository,
                                            PlantTypeRepository plantTypeRepository,
                                            PlantRepository plantRepository,
                                            PasswordEncoder passwordEncoder) {
        return arguments -> {
            AppUser admin = new AppUser(
                    "admin", passwordEncoder.encode("admin123"), Role.ADMIN);
            AppUser user = new AppUser(
                    "user", passwordEncoder.encode("user123"), Role.USER);
            userRepository.save(admin);
            userRepository.save(user);

            PlantType fruit = plantTypeRepository.save(new PlantType("VOĆE"));
            PlantType vegetable = plantTypeRepository.save(new PlantType("POVRĆE"));
            PlantType decoration = plantTypeRepository.save(new PlantType("UKRAS"));

            plantRepository.save(new Plant(
                    "Jabuka", new BigDecimal("2.50"), fruit));
            plantRepository.save(new Plant(
                    "Rajčica", new BigDecimal("3.20"), vegetable));
            plantRepository.save(new Plant(
                    "Ruža", new BigDecimal("12.00"), decoration));
        };
    }
}
