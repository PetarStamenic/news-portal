package com.example.projekat_backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer id;
    @NotNull(message = "Author is required")
    @NotEmpty(message = "Author is required")
    private String author;
    @NotNull(message = "Content is required")
    @NotEmpty(message = "Content is required")
    private String content;
    @JsonProperty("creation_date")
    private Date creationDate;
    private News news;
}
