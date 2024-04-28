package com.example.projekat_backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class News {
    private Integer id;
    @NotNull(message = "Title is required")
    @NotEmpty(message = "Title is required")
    private String title;
    @NotNull(message = "Content is required")
    @NotEmpty(message = "Content is required")
    private String content;
    @JsonProperty("creation_date")
    private Date creationDate;
    private User author;
    private Integer views;
    private String category;
    private List<String> tags = new ArrayList<>();
}
