package com.example.projekat_backend.router;

import com.example.projekat_backend.model.Comment;
import com.example.projekat_backend.model.News;
import com.example.projekat_backend.model.Tag;
import com.example.projekat_backend.service.NewsService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/news")
public class NewsRouter {
    @Inject
    private NewsService newsService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> all()
    {
        return this.newsService.allNews();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public News find(@PathParam("id") Integer id) {return this.newsService.findNews(id);}

    @GET
    @Path("/visits")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> allNewsByVisits(){return this.newsService.allNewsByVisits();}

    @GET
    @Path("/category/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> allByCategory(@PathParam("name") String name) {return this.newsService.allByCategory(name);}

    @GET
    @Path("/tag/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> allByTag(@PathParam("id") Integer id) {return this.newsService.allByTag(id);}

    @GET
    @Path("/newsTag/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> allTagByNews(@PathParam("id") Integer id) {return this.newsService.allTagByNews(id);}

    @GET
    @Path("/comments/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> allCommentsByNews(@PathParam("id") Integer id) {return this.newsService.allCommentsByNews(id);}

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public News create(@Valid News news) {return this.newsService.addNews(news);}

    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public News updateNews(@Valid News news) {return this.newsService.updateNews(news);}

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") Integer id) {this.newsService.deleteNews(id);}
}
