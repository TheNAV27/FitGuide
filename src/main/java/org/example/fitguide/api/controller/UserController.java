package org.example.fitguide.api.controller;

import org.example.fitguide.api.model.User;
import org.example.fitguide.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Integer id){
        Optional user = userService.getUser(id);

        return (User) user.orElse(null);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getUserList();
    }

}
