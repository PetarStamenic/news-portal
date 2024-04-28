package com.example.projekat_backend.repository.comment;

import com.example.projekat_backend.model.Comment;

import java.util.List;

public interface CommentRepository {
    List<Comment> allComments();
    Comment findComment(Integer id);
    Comment addComment(Comment comment);
    void deleteComment(Integer id);
}
