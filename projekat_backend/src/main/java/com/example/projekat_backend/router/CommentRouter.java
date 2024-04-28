package com.example.projekat_backend.router;

import com.example.projekat_backend.model.Comment;
import com.example.projekat_backend.service.CommentService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/comments")
public class CommentRouter {
    @Inject
    private CommentService commentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> all() {return this.commentService.allComments();}

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Comment find(@PathParam("id") Integer id) {return this.commentService.findComment(id);}

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Comment create(@Valid Comment comment) {return this.commentService.addComment(comment);}

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") Integer id) {this.commentService.deleteComment(id);}
}
