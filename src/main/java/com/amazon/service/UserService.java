package com.amazon.service;

import com.amazon.dto.LoginRequest;
import com.amazon.model.Users;
import com.amazon.repo.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepo;
    private AuthenticationManager authenticationManager;
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserService(UserRepository userRepo, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public ResponseEntity<?> register(Users users) {
        users.setPassword(encoder.encode(users.getPassword()));

        //Check if user already exist in DB
        boolean exist = userRepo.existsByUsername(users.getUsername());

        if(exist) return new ResponseEntity<>("User Already Exists", HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(userRepo.save(users), HttpStatus.OK);
    }
    
    public List<Users> getUsers() {
        return userRepo.findAll();
    }


    public String loginVerify(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        if(authentication.isAuthenticated()) return jwtService.generateToken(loginRequest.getUsername());
        return "Login Failed";
    }
}
