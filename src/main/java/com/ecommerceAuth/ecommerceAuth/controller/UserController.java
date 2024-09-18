package com.ecommerceAuth.ecommerceAuth.controller;

import com.ecommerceAuth.ecommerceAuth.model.entities.User;
import com.ecommerceAuth.ecommerceAuth.service.AuthenticationService;
import com.ecommerceAuth.ecommerceAuth.service.JwtService;
import com.ecommerceAuth.ecommerceAuth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/auth2")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        System.out.println("HIT");
        return userService.createUser(user).map(newCustomer -> new ResponseEntity<>(newCustomer, OK))
                .orElse(new ResponseEntity<>(CONFLICT));
    }

    @RequestMapping(path = "/user/{username}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("username") String username) {
        System.out.println("GETTING USER");
        return userService.getUserByID(username).map(newUserData -> new ResponseEntity<>(newUserData, OK))
                .orElse(new ResponseEntity<>(CONFLICT));
    }
}
