package com.amazon.controller;

import com.amazon.dto.LoginRequest;
import com.amazon.model.Users;
import com.amazon.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users user) {
        return userService.register(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginVerify(@RequestBody LoginRequest loginRequest) {
        String token = userService.loginVerify(loginRequest);
        if ("Login Failed".equals(token)) {
            return new ResponseEntity<>(Map.of("error", "Invalid credentials"), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(Map.of("token", token), HttpStatus.OK);

    }
}