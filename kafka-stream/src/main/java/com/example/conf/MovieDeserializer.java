package com.example.conf;

import com.example.models.IncomingMovie;
import com.example.models.Movie;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class MovieDeserializer extends ObjectMapperDeserializer<IncomingMovie> {

    public MovieDeserializer() {
        super(IncomingMovie.class);
    }
}
