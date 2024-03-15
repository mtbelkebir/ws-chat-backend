package dz.mtbelkebir.wschat.api.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dz.mtbelkebir.wschat.api.auth.AuthenticationRequest;
import dz.mtbelkebir.wschat.api.auth.AuthenticationResponse;
import dz.mtbelkebir.wschat.api.auth.RegistrationRequest;
import dz.mtbelkebir.wschat.api.exception.UserAlreadyExistsException;
import dz.mtbelkebir.wschat.api.model.User;
import dz.mtbelkebir.wschat.api.model.UserRepository;
import dz.mtbelkebir.wschat.api.model.UserRole;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public void register(RegistrationRequest request) throws UserAlreadyExistsException {
        if (userRepository.getByUsername(request.username()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        User newUser = User.builder()
                .username(request.username())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .password(passwordEncoder.encode(request.password()))
                .role(UserRole.USER)
                .build();

        userRepository.save(newUser);
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        User user = userRepository.getByUsername(request.username()).orElseThrow();
        String jwt = jwtService.generateToken(user);
        return new AuthenticationResponse(jwt);
    }
}
