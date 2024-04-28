package com.example.projekat_backend.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @NotNull(message = "Nmae is required")
    @NotEmpty(message = "Name is required")
    private String name;
    @NotNull(message = "Description is required")
    @NotEmpty(message = "Descriptiopn is required")
    private String description;
}
