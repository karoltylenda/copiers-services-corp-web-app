package com.tytanisukcesu.demo.service;

import com.tytanisukcesu.demo.entity.UserEntity;
import com.tytanisukcesu.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAll(){
        return userRepository.getAll();
    }
}
