package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.User;
import com.tytanisukcesu.copiers.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

   /***
    *Repository - Spring Config layer
    * Based on received data - get all the details about the User.
    */

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isPresent()){
            return userOptional.get();
        }else{
            return new User();
        }
    }
}
