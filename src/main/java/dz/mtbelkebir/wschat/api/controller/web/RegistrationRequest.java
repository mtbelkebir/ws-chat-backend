package dz.mtbelkebir.wschat.api.controller.web;

import lombok.NonNull;

public record RegistrationRequest(
        @NonNull String username,
        @NonNull String password,
        @NonNull String firstName,
        @NonNull String lastName) {
}
