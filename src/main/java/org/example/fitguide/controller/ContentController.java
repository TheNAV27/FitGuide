package org.example.fitguide.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class ContentController {
    @PostMapping("/login")
    public String Login(){
        return "login";
    }
    @GetMapping("/Register")
    public String Register() {
        return "register";
    }
}
