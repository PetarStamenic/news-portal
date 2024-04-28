package com.example.projekat_backend;

import com.example.projekat_backend.repository.category.CategoryRepository;
import com.example.projekat_backend.repository.category.MySQLCategoryRepository;
import com.example.projekat_backend.repository.comment.CommentRepository;
import com.example.projekat_backend.repository.comment.MySQLCommentRepoository;
import com.example.projekat_backend.repository.news.MySQLNewsRepository;
import com.example.projekat_backend.repository.news.NewsRepository;
import com.example.projekat_backend.repository.tag.MySQLTagRepository;
import com.example.projekat_backend.repository.tag.TagRepository;
import com.example.projekat_backend.repository.user.MySQLUserRepository;
import com.example.projekat_backend.repository.user.UserRepository;
import com.example.projekat_backend.service.*;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;


@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {
    public HelloApplication() {
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);


        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(MySQLNewsRepository.class).to(NewsRepository.class).in(Singleton.class);
                this.bind(MySQLUserRepository.class).to(UserRepository.class).in(Singleton.class);
                this.bind(MySQLCategoryRepository.class).to(CategoryRepository.class).in(Singleton.class);
                this.bind(MySQLTagRepository.class).to(TagRepository.class).in(Singleton.class);
                this.bind(MySQLCommentRepoository.class).to(CommentRepository.class).in(Singleton.class);

                this.bindAsContract(NewsService.class);
                this.bindAsContract(UserService.class);
                this.bindAsContract(CategoryService.class);
                this.bindAsContract(TagService.class);
                this.bindAsContract(CommentService.class);
            }
        };

        register(binder);

        // Ucitavamo resurse
        packages("com.example.projekat_backend");
    }
}