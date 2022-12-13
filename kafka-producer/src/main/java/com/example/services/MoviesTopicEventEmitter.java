package com.example.services;


import com.example.models.Movie;
import io.smallrye.reactive.messaging.kafka.api.OutgoingKafkaRecordMetadata;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MoviesTopicEventEmitter {

    @Inject
    @Channel("movies-out")
    Emitter<Movie> emitter;

    public void emmitDirectPayload(Movie m) {
        emitter.send(m);
    }

    public void emmitWithMetadata(Movie m) {
        var metadata = OutgoingKafkaRecordMetadata.builder()
                .withKey(m.getId())
                .build();
        emitter.send(Message.of(m).addMetadata(metadata));

    }

}
