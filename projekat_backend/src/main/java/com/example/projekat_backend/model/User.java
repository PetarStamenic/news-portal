package com.example.projekat_backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    @NotNull(message = "First name is required")
    @NotEmpty(message = "First nmae is required")
    @JsonProperty("first_name")
    private String firstName;
    @NotNull(message = "Last name is required")
    @NotEmpty(message = "Last name is required")
    @JsonProperty("last_name")
    private String lastName;
    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    private String email;
    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    private String password;
    private int type;
    private int status;
}
