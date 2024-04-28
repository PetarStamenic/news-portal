package com.example.projekat_backend.router;

import com.example.projekat_backend.model.Category;
import com.example.projekat_backend.service.CategoryService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;

@Path("/category")
public class CategoryRouter {
    @Inject
    private CategoryService categoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> all(){return this.categoryService.allCategory();}

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category find(@PathParam("name") String name){return this.categoryService.findCategory(name);}


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Category create(@Valid Category category) {return this.categoryService.addCategory(category);}

    @POST
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category update(@Valid Category category, @PathParam("name") String name) {return this.categoryService.updateCategory(category, name);}

    @DELETE
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("name") String name) {this.categoryService.deleteCategory(name);}
}
