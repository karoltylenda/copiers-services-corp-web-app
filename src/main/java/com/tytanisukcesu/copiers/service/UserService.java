package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.User;
import com.tytanisukcesu.copiers.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(User user){
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if(userOptional.isEmpty()){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole("ROLE_USER");
            userRepository.save(user);
        }
    }

}
