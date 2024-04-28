package com.example.projekat_backend.repository.news;

import com.example.projekat_backend.model.Comment;
import com.example.projekat_backend.model.News;

import java.util.List;

public interface NewsRepository {

    List<News> allNews();
    List<News> allNewsByVisits();
    News findNews(Integer id);
    List<News> allByCategory(String name);
    List<News> allByTag(Integer id);
    List<String> allTagByNews(Integer id);
    List<Comment> allCommentsByNews(Integer id);
    News addNews(News news);
    News updateNews(News news);
    void deleteNews(Integer id);
}
