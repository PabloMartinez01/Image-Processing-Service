package com.pablodev.imageprocessing.controllers;

import com.pablodev.imageprocessing.dto.authentication.AuthenticationRequest;
import com.pablodev.imageprocessing.dto.authentication.AuthenticationResponse;
import com.pablodev.imageprocessing.dto.authentication.RegistrationRequest;
import com.pablodev.imageprocessing.services.authentication.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegistrationRequest registrationRequest) {
        this.authenticationService.register(registrationRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticationRequest);
        return ResponseEntity.ok().body(authenticationResponse);
    }

}
