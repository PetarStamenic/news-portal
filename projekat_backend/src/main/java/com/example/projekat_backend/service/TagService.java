package com.example.projekat_backend.service;

import com.example.projekat_backend.model.Tag;
import com.example.projekat_backend.repository.tag.TagRepository;

import javax.inject.Inject;
import java.util.List;

public class TagService {
    public TagService() {}
    @Inject
    private TagRepository tagRepository;
    public List<Tag> allTags() {
        return this.tagRepository.allTags();
    }
    public Tag findTag(String id) {
        return this.tagRepository.findTag(id);
    }
    public Tag findTagById(Integer id) {
        return this.tagRepository.findTagById(id);
    }
    public Tag addTag(String tag) {
        return this.tagRepository.addTag(tag);
    }
    public void deleteTag(Integer id) {this.tagRepository.deleteTag(id);}
}
