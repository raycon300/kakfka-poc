package com.example.services;

import com.example.models.Movie;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;

@ApplicationScoped
public class NewMoviesHandler {

    Logger logger = Logger.getLogger(NewMoviesHandler.class);

    private static final HashMap<String, Movie> IDEMPOTENT_DATA_STORE = new HashMap<>();


    @Incoming("movies-in")
    public void synchronousSinks(ConsumerRecord<String, Movie> movie) {
        processDataRecord(movie);
    }


    @Incoming("fallback-movies-in")
    public void synchronousFailoverSink(ConsumerRecord<String, Movie> movie) {
        processDataRecord(movie);
    }


    private void processDataRecord(ConsumerRecord<String, Movie> movie) {
        var idempotentKey = generateIdempotentKey(movie);

        // doesn't matter if the record is from the main or fallback cluster
        // it will be processed only once
        if (hasNotBeenProcessed(idempotentKey)) {
            logger.infov("movie id: {0}", movie.value().getId());
            logger.infov("movie key: {0}", movie.key());
            logger.infov("movie partition: {0}", movie.partition());
            logger.infov("movie offset: {0}", movie.offset());
        }

        storeIdempotentDataIfAbsent(idempotentKey, movie.value());
    }

    private String generateIdempotentKey(ConsumerRecord<String, Movie> movie) {
        // this is a simple way to generate a unique key
        return movie.value().getId() + movie.partition() + movie.offset();
    }

    private void storeIdempotentDataIfAbsent(String idempotentKey, Movie movie) {
        // this must be a external data store
        IDEMPOTENT_DATA_STORE.putIfAbsent(idempotentKey, movie);
    }

    private boolean hasNotBeenProcessed(String idempotentKey) {
        return !IDEMPOTENT_DATA_STORE.containsKey(idempotentKey);
    }
}
