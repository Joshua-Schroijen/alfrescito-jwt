package be.joshuaschroijen.alfrescito.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import be.joshuaschroijen.alfrescito.components.JwtUtils;
import be.joshuaschroijen.alfrescito.dto.AuthenticationRequest;
import be.joshuaschroijen.alfrescito.dto.AuthenticationResponse;
import be.joshuaschroijen.alfrescito.dto.UserRegistrationRequest;
import be.joshuaschroijen.alfrescito.service.AlfrescitoUserDetailsService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final AlfrescitoUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
        @RequestBody AuthenticationRequest request
    ){
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final UserDetails user = userDetailsService.loadUserByUsername(request.getEmail());
        if (user != null) {
            return ResponseEntity.ok(
                new AuthenticationResponse(jwtUtils.generateToken(user))
            );
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
        @RequestBody UserRegistrationRequest request
    ) throws Exception {
        userDetailsService.saveUser(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}