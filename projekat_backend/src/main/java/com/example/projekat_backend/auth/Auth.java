package com.example.projekat_backend.auth;

import com.example.projekat_backend.router.NewsRouter;
import com.example.projekat_backend.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

@Provider
public class Auth implements ContainerRequestFilter {
    @Inject
    UserService userService;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        if(auth(containerRequestContext)){
            String token = containerRequestContext.getHeaderString("Authorization");
            if (token != null && token.startsWith("Bearer "))
                token = token.replace("Bearer ","");
            if(!this.userService.isAuthorized(token))
                containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
    private boolean auth(ContainerRequestContext requestContext){
        if(requestContext.getUriInfo().getPath().contains("login"))
            return false;
        List<Object> matchedResources = requestContext.getUriInfo().getMatchedResources();
        for (Object matched : matchedResources){
            if(matched instanceof NewsRouter)
                return true;
        }
        return false;
    }
}
