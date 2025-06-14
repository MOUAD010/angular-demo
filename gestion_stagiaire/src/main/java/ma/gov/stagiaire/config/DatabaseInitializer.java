package ma.gov.stagiaire.config;

import lombok.extern.slf4j.Slf4j;
import ma.gov.stagiaire.entities.RoleEntity;
import ma.gov.stagiaire.entities.UserEntity;
import ma.gov.stagiaire.repositories.RoleRepository;
import ma.gov.stagiaire.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
@Slf4j
public class DatabaseInitializer {

    @Bean
    public CommandLineRunner initDatabase(RoleRepository roleRepository, 
                                         UserRepository userRepository, 
                                         PasswordEncoder passwordEncoder) {
        return args -> {
            log.info("Initializing database with default roles and admin user");
            
            // Create default roles if they don't exist
            if (!roleRepository.existsByName("USER")) {
                RoleEntity userRole = new RoleEntity();
                userRole.setName("USER");
                userRole.setDescription("Standard user role");
                roleRepository.save(userRole);
                log.info("Created USER role");
            }

            if (!roleRepository.existsByName("MODERATOR")) {
                RoleEntity modRole = new RoleEntity();
                modRole.setName("MODERATOR");
                modRole.setDescription("Moderator role with additional privileges");
                roleRepository.save(modRole);
                log.info("Created MODERATOR role");
            }

            if (!roleRepository.existsByName("ADMIN")) {
                RoleEntity adminRole = new RoleEntity();
                adminRole.setName("ADMIN");
                adminRole.setDescription("Administrator role with full privileges");
                roleRepository.save(adminRole);
                log.info("Created ADMIN role");
            }

            // Create default admin user if it doesn't exist
            if (!userRepository.existsByLogin("admin")) {
                UserEntity adminUser = new UserEntity();
                adminUser.setName("Administrator");
                adminUser.setLogin("admin");
                adminUser.setPassword(passwordEncoder.encode("admin123"));

                Set<RoleEntity> roles = new HashSet<>();
                roleRepository.findByName("ADMIN").ifPresent(roles::add);
                adminUser.setRoles(roles);

                userRepository.save(adminUser);
                log.info("Created default admin user");
            }
        };
    }
} 