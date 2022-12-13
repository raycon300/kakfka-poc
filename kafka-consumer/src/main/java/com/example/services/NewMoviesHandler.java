package com.example.services;

import com.example.models.Movie;
import org.eclipse.microprofile.reactive.messaging.*;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class NewMoviesHandler {

    //  @Incoming("movies-in")
    public CompletionStage<Void> asynchronousSink(Message<Movie> msg) {
        return CompletableFuture.runAsync( () -> {

            System.out.println("called >>"+ msg.getPayload().getId());
        });
    }

    @Incoming("movies-in")
    public void synchronousSink(Movie movie) {
        System.out.println("called >>"+ movie.getId());
    }
}
