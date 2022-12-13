package com.example.service;

import com.example.models.IncomingMovie;
import com.example.models.Movie;
import org.eclipse.microprofile.reactive.messaging.*;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieStreamProcessor {

    private final ExtraInfoGenerator extraInfoGenerator;

    public MovieStreamProcessor(ExtraInfoGenerator generator) {
        this.extraInfoGenerator = generator;
    }

    @Incoming("movies-in")
    @Outgoing("movies-out")
    public Message<Movie> toUpperCase(Message<IncomingMovie> incoming) {

        var movie = extraInfoGenerator.generate( incoming.getPayload() );

        return Message.of(movie);
    }

}
