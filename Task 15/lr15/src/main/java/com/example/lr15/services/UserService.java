package com.example.lr15.services;

import com.example.lr15.entities.User;
import com.example.lr15.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByUserName(String username) {
        User user = userRepository.findByusername(username);
        if (user == null) throw new UsernameNotFoundException(username);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public String getRealNameByUsername(String username) {
        User user = userRepository.findByusername(username);
        return (user != null) ? user.getName() : "";
    }

    public String getAuthorityByusername(String username) {
        User user = userRepository.findByusername(username);
        return (user != null && user.getAuthority() != null) ? user.getAuthority().getAuthority() : null;
    }
//    public void encode(String username) {
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        User user = userRepository.findByusername(username);
//        String encr = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encr);
//        userRepository.save(user);
//    }
}
