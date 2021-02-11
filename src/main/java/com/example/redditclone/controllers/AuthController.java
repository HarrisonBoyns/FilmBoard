package com.example.redditclone.controllers;

import com.example.redditclone.dto.AuthenticationResponse;
import com.example.redditclone.dto.LoginRequest;
import com.example.redditclone.dto.RegisterRequest;
import com.example.redditclone.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aspectj.weaver.ResolvedPointcutDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated

@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
//    DTO class / object
    @PostMapping(path = "/signup")
    public ResponseEntity<String> signUp(@RequestBody @Valid RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity<String>("User Registration Suucessful", HttpStatus.CREATED);
    }

    @GetMapping(path = "/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return new ResponseEntity<String>("Account Activated", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest){

        return new ResponseEntity<AuthenticationResponse>(authService.login(loginRequest), HttpStatus.OK);
    }
}
