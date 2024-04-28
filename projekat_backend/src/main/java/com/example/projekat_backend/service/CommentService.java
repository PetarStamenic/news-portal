package com.example.projekat_backend.service;

import com.example.projekat_backend.model.Comment;
import com.example.projekat_backend.repository.comment.CommentRepository;

import javax.inject.Inject;
import java.util.List;

public class CommentService {

    @Inject
    private CommentRepository commentRepository;
    public List<Comment> allComments() {
        return this.commentRepository.allComments();
    }
    public Comment findComment(Integer id) {
        return this.commentRepository.findComment(id);
    }
    public Comment addComment(Comment comment) {
        return this.commentRepository.addComment(comment);
    }
    public void deleteComment(Integer id) {
        this.commentRepository.deleteComment(id);
    }
}
