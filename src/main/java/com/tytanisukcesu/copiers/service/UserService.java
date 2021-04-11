package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Device;
import com.tytanisukcesu.copiers.entity.User;
import com.tytanisukcesu.copiers.repository.UserRepository;
import com.tytanisukcesu.copiers.service.exception.ModelNotFoundException;
import com.tytanisukcesu.copiers.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DeviceService deviceService;
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    public void save(User user){
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if(userOptional.isEmpty()){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(id, "user"));
    }

//    public List<Device> findDevicesByUserId(Long id){
//        Optional<User> optionalUser = userRepository.findById(id);
//        if(optionalUser.isPresent()){
//            List<Device> devices = deviceService.findByCustomer(optionalUser.get().getCustomer());
//            return devices;
//        } else {
//            LOGGER.warning("User for id " + id + " doesn't exist");
//            return new ArrayList<>();
//        }
//    }

    public User findUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException(username));
    }

    public boolean delete(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            userRepository.delete(optionalUser.get());
            return true;
        } else return false;
    }

    public void passwordReset(Long id, String password) {
        User user = findById(id);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public User update(User user) {
        User userToUpdate = findById(user.getId());
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setRole(user.getRole());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setCustomer(user.getCustomer());
        User userUpdated = userRepository.save(userToUpdate);
        return userUpdated;
    }
}
