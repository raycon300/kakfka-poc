package com.example.resources;

import com.example.services.MoviesTopicEventEmitter;
import com.example.models.Movie;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/produce")
public class MoviesResource {

    private final MoviesTopicEventEmitter producer;

    public MoviesResource(MoviesTopicEventEmitter producer) {
        this.producer = producer;
    }

    @GET
    @Path("/simple")
    public Response send() {
        var movie = new Movie("Abcd");
        producer.emmitDirectPayload(movie);
        return Response.ok().build();
    }

    @GET
    @Path("/metadata")
    public Response sendWithMetadata() {
        var movie = new Movie("Dbca");
        producer.emmitWithMetadata(movie);
        return Response.ok().build();
    }
}
