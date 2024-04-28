package com.example.projekat_backend.repository.tag;

import com.example.projekat_backend.model.Tag;

import java.util.List;

public interface TagRepository {
    List<Tag> allTags();
    Tag findTag(String id);
    Tag findTagById(Integer id);
    Tag addTag(String tag);
    void deleteTag(Integer id);
}
