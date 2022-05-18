package com.example.timeregistration.security;

import com.example.timeregistration.security.dto.AuthenticationRequestDTO;
import com.example.timeregistration.security.dto.UserRegistrationDto;
import com.example.timeregistration.security.dto.UserRegisteredDto;
import com.example.timeregistration.security.model.Role;
import com.example.timeregistration.model.User;
import com.example.timeregistration.security.jwt.JwtTokenProvider;
import com.example.timeregistration.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@Log4j2
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthenticationManager authenticationManager, ModelMapper modelMapper, UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisteredDto> registerUser(@Valid @RequestBody UserRegistrationDto userRegistrationDto) {

        User savedUser = userService.register(modelMapper.map(userRegistrationDto, User.class), Role.USER);

        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(savedUser, UserRegisteredDto.class));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<UserRegisteredDto> registerAdmin(@Valid @RequestBody UserRegistrationDto userRegistrationDto) {

        User savedUser = userService.register(modelMapper.map(userRegistrationDto, User.class), Role.ADMIN);

        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(savedUser, UserRegisteredDto.class));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<Object,Object>> authenticate(@RequestBody AuthenticationRequestDTO request) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            User user = userService.findByEmail(request.getEmail());
            String token = jwtTokenProvider.createToken(request.getEmail(), user.getRole());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", request.getEmail());
            response.put("token", token);
            return ResponseEntity.ok(response);
    }

}
