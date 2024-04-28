package com.example.projekat_backend.router;

import com.example.projekat_backend.model.User;
import com.example.projekat_backend.req.Login;
import com.example.projekat_backend.service.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/users")
public class UserRouter {
    @Inject
    private UserService userService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> all(){return this.userService.allUser();}


    @GET
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public User find(@PathParam("email") String email) {return this.userService.findUser(email);}



    @GET
    @Path("/status/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public void userActivity(@PathParam("email") String email) {this.userService.userActivity(email);}


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User create(@Valid User user) {return this.userService.addUser(user);}

    @POST
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public User updateUser(@Valid User user, @PathParam("email") String email){return this.userService.updateUser(user, email);}

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(@Valid Login loginRequest){
        System.out.println(loginRequest);
        Map<String, String> response = new HashMap<>();
        String jwt = this.userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (jwt == null) {
            response.put("message", "I ve never met this man in my life");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }
        response.put("jwt",jwt);
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/delete/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("email") String email) {this.userService.deleteUser(email);}
}
