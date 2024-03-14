package dz.mtbelkebir.wschat.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dz.mtbelkebir.wschat.api.controller.web.AuthenticationRequest;
import dz.mtbelkebir.wschat.api.controller.web.AuthenticationResponse;
import dz.mtbelkebir.wschat.api.controller.web.RegistrationRequest;
import dz.mtbelkebir.wschat.api.exception.UserAlreadyExistsException;
import dz.mtbelkebir.wschat.api.service.AuthenticationService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        try {
            authenticationService.register(request);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This username is taken");
        }

        return ResponseEntity.ok().body("Registration successful !");
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        var reponse = authenticationService.login(request);
        return ResponseEntity.ok().body(reponse);
    }
    

}
