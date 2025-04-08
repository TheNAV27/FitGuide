package org.example.fitguide.controller;


import org.example.fitguide.model.User;
import org.example.fitguide.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class ContentController {


    private final UserRepository userRepository;

    public ContentController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @PostMapping("/Register")
   public ResponseEntity<?> Register(@RequestParam String username, @RequestParam String password) {
        if(userRepository.findByUsername(username).isPresent()) {
            return ResponseEntity.status(400).body("Invalid username or password");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        return ResponseEntity.status(201).body("User registered successfully");
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        var existingUSer = userRepository.findByUsername(user.getUsername());
        if(existingUSer.isPresent()){
            return ResponseEntity.status(400).body("Invalid username or password");
        }
        return ResponseEntity.status(201).body("User logged in successfully");
    }
}
