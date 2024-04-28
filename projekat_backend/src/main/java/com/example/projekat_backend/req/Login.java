package com.example.projekat_backend.req;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    private String email;
    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    private String password;
}
