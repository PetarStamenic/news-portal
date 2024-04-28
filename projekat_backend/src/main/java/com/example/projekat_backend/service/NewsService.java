package com.example.projekat_backend.service;

import com.example.projekat_backend.model.Comment;
import com.example.projekat_backend.model.News;
import com.example.projekat_backend.model.Tag;
import com.example.projekat_backend.repository.news.NewsRepository;

import javax.inject.Inject;
import java.util.List;

public class NewsService {
    @Inject
    private NewsRepository newsRepository;

    public List<News> allNews() {
        return this.newsRepository.allNews();
    }
    public News findNews(Integer id) {
        return this.newsRepository.findNews(id);
    }
    public List<News> allNewsByVisits() {
        return this.newsRepository.allNewsByVisits();
    }
    public List<News> allByCategory(String name) {
        return this.newsRepository.allByCategory(name);
    }
    public List<News> allByTag(Integer id) {
        return this.newsRepository.allByTag(id);
    }
    public List<String> allTagByNews(Integer id) {
        return this.newsRepository.allTagByNews(id);
    }
    public List<Comment> allCommentsByNews(Integer id) {
        return this.newsRepository.allCommentsByNews(id);
    }
    public News addNews(News news) {
        return this.newsRepository.addNews(news);
    }
    public News updateNews(News news) {
        return this.newsRepository.updateNews(news);
    }
    public void deleteNews(Integer id) {
        this.newsRepository.deleteNews(id);
    }
}
