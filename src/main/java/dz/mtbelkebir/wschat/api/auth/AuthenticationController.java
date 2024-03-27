package dz.mtbelkebir.wschat.api.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dz.mtbelkebir.wschat.api.exception.UserAlreadyExistsException;
import dz.mtbelkebir.wschat.api.util.GenericResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<GenericResponse<?>> register(@RequestBody RegistrationRequest request) {
        try {
            authenticationService.register(request);
        } catch (UserAlreadyExistsException e) {
            GenericResponse<?> res = GenericResponse.builder().success(false).message("This username is taken").build();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
        }
        GenericResponse<?> res = GenericResponse.builder().success(true).message("Registration successful !").build();
        return ResponseEntity.ok().body(res);
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponse<?>> login(@RequestBody AuthenticationRequest request) {
        var response = authenticationService.login(request);
        return ResponseEntity.ok().body(GenericResponse.builder().success(true).data(response).build());
    }

}
