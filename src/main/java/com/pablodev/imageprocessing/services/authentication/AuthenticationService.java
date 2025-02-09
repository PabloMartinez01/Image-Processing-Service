package com.pablodev.imageprocessing.services.authentication;

import com.pablodev.imageprocessing.dto.authentication.AuthenticationRequest;
import com.pablodev.imageprocessing.dto.authentication.AuthenticationResponse;
import com.pablodev.imageprocessing.dto.authentication.RegistrationRequest;

public interface AuthenticationService {
    void register(RegistrationRequest registrationRequest);
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
