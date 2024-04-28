package com.example.projekat_backend.router;

import com.example.projekat_backend.model.Tag;
import com.example.projekat_backend.service.TagService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/tags")
public class TagRouter {
    @Inject
    private TagService tagService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tag> all(){return this.tagService.allTags();}

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Tag find(@PathParam("name") String id) {return this.tagService.findTag(id);}

    @GET
    @Path("by/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Tag findTagById(@PathParam("id") Integer id) {return this.tagService.findTagById(id);}


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Tag create(@Valid String tag) {return this.tagService.addTag(tag);}


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") Integer id) {this.tagService.deleteTag(id);}
}
