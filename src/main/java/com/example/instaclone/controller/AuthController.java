package com.example.instaclone.controller;

import com.example.instaclone.model.User;
import com.example.instaclone.security.dto.AuthenticationDto;
import com.example.instaclone.security.dto.RegisteredDto;
import com.example.instaclone.security.dto.RegistrationDto;
import com.example.instaclone.security.jwt.JwtTokenProvider;
import com.example.instaclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        return ResponseEntity.ok(jwtTokenProvider.createToken(request.getUsername()));

    }

    @PostMapping("/register")
    public ResponseEntity<RegisteredDto> register(@Valid @RequestBody RegistrationDto registrationDto) {
        User savedUser = userService.register(modelMapper.map(registrationDto, User.class));
        return ResponseEntity.ok(modelMapper.map(savedUser, RegisteredDto.class));

    }

}
