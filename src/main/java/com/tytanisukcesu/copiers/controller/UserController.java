package com.tytanisukcesu.copiers.controller;

import com.tytanisukcesu.copiers.dto.UserDto;
import com.tytanisukcesu.copiers.entity.User;
import com.tytanisukcesu.copiers.service.CustomerService;
import com.tytanisukcesu.copiers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final CustomerService customerService;

    @GetMapping
    public List<UserDto> getAll(){
        return userService.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public UserDto getById(@PathVariable("id") Long id){
        User user = userService.findById(id);
        return convertToDto(user);
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    private User convertToEntity(UserDto userDto){
        User user = modelMapper.map(userDto, User.class);
        return user;
    }
}
