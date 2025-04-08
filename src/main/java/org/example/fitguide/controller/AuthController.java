package org.example.fitguide.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.example.fitguide.model.User;
import org.example.fitguide.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/auth")
public class AuthController {


    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @PostMapping("/register")
   public ResponseEntity<?> Register(@RequestParam String username, @RequestParam String password) {
        if(userRepository.findByUsername(username).isPresent()) {
            return ResponseEntity.status(400).body("Username is already in use");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        return ResponseEntity.status(201).body("User registered successfully");
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        var existingUser = userRepository.findByUsername(user.getUsername());
        if(existingUser.isEmpty() || !user.getPassword().equals(existingUser.get().getPassword())) {
            return ResponseEntity.status(400).body("Invalid username or password");
        }
        return ResponseEntity.status(201).body("User logged in successfully");
    }
    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return ResponseEntity.ok("logout successful");
    }
}
