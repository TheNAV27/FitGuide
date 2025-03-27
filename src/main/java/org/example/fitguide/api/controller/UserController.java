package org.example.fitguide.api.controller;

import org.example.fitguide.api.model.User;
import org.example.fitguide.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user")
    public User getUser(@RequestParam Integer id){
        Optional user = userService.getUser(id);

        return (User) user.orElse(null);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getUserList();
    }

}
