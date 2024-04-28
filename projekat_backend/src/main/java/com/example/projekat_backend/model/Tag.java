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
public class Tag {
    private Integer id;
    @NotNull(message = "Tag is required")
    @NotEmpty(message = "Tag is required")
    @JsonProperty("tag_name")
    private String tagName;
}
