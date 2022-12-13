package com.example.service;

import com.example.models.IncomingMovie;
import com.example.models.Movie;


import javax.enterprise.context.ApplicationScoped;
import java.util.Random;
import java.util.random.RandomGenerator;

@ApplicationScoped
public class ExtraInfoGenerator {

    public Movie generate(IncomingMovie incomingMovie){
        var duration = new Random().nextInt(1 , 6) * 10;
        var year = new Random().nextInt(2019, 2023);

        var movie = Movie.createFrom(incomingMovie);
        movie.setYear(year+"");
        movie.setDuration("01:"+duration+":00");
        return movie;
    }
}
