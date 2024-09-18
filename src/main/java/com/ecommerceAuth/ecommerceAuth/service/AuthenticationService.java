package com.ecommerceAuth.ecommerceAuth.service;

import com.ecommerceAuth.ecommerceAuth.model.dtos.LoginUserDto;
import com.ecommerceAuth.ecommerceAuth.model.dtos.RegisterUserDto;
import com.ecommerceAuth.ecommerceAuth.model.entities.User;
import com.ecommerceAuth.ecommerceAuth.model.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        User user = User.builder()
                .username(input.getUsername())
                .password(passwordEncoder.encode(input.getPassword()))
                .build();

        System.out.println(user);

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        //Calls getUserByUsername and validates password. Raises exception if incorrect
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        Optional<User> userop=  userRepository.getUserByUsername(input.getUsername());
        System.out.println("Autenticando:"+ userop);
        return userop.orElseThrow();
    }
}
