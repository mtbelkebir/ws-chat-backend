package dz.mtbelkebir.wschat.api.auth;

import lombok.NonNull;

public record RegistrationRequest(
        @NonNull String username,
        @NonNull String password,
        @NonNull String firstName,
        @NonNull String lastName) {
}
