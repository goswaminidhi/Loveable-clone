package com.loveableclone.dto.auth;

import jakarta.validation.constraints.*;

public record LoginRequest(
        @Email @NotBlank String username,

        @Size(min = 4, max = 10)
        String password
) {
}
