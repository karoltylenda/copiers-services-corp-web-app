package com.tytanisukcesu.copiers.configuration;

import com.tytanisukcesu.copiers.entity.User;
import com.tytanisukcesu.copiers.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class LoadAdministrationConfig {

    public LoadAdministrationConfig(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        if(userRepository.findByUsername("admin").isEmpty()){
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole("ROLE_ADMIN");
            userRepository.save(admin);
        }else if(userRepository.findByUsername("user").isEmpty()){
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setRole("ROLE_USER");
            userRepository.save(user);
        }
    }

}
