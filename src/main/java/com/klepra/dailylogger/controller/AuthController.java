/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klepra.dailylogger.controller;

import com.klepra.dailylogger.model.User;
import com.klepra.dailylogger.service.UserService;
import com.klepra.dailylogger.utils.JwtTokenUtil;
import java.net.URI;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 *
 * @author klemen
 */
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @RequestMapping(value = "/login", method = POST)
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        Objects.requireNonNull(user.getUsername());
        Objects.requireNonNull(user.getPassword());

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (DisabledException e) {
            throw new DisabledException("User is disabled!", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Bad credentials!", e);
        }
        // Reload so we can generate the token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(token);
    }

    @RequestMapping(value = "/register", method = POST)
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        //to-do
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        User newUser = userService.saveUser(user);
        return ResponseEntity.created(URI.create(newUser.getId().toString())).build();
    }
}
