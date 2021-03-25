package com.tytanisukcesu.copiers.servlet;

import com.tytanisukcesu.copiers.dto.UserDto;
import com.tytanisukcesu.copiers.entity.User;
import com.tytanisukcesu.copiers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserServlet {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String findAll(Model model, Principal principal){
        model.addAttribute("users", userService.findAll());
        model.addAttribute("loggedUser", userService.findUserByUsername(principal.getName()));
        return "pages/users";
    }

    @PostMapping
    public RedirectView addNewUser(UserDto userDto){
        userService.save(convertToEntity(userDto));
        return new RedirectView("/users");
    }

    @PostMapping(value = "/delete")
    public RedirectView deleteUser(@RequestParam Long id){
        if (userService.delete(id)){
            return new RedirectView("/users");
        } else {
            return new RedirectView("/404.html");
        }
    }

    @PostMapping(value = "/password_reset")
    public RedirectView passwordReset(Long id, String password){
        userService.passwordReset(id, password);
        return new RedirectView("/users");
    }

    @PostMapping(value = "/update")
    public RedirectView updateUser(UserDto userDto){
        userService.update(convertToEntity(userDto));
        return new RedirectView("/users");
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
