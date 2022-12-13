package com.example.models;

public class Movie {

    private String name;
    private String id;
    private String year;
    private String duration;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


    public static Movie createFrom(IncomingMovie in){
        var movie = new Movie();
        movie.id = in.getId();
        movie.name = in.getName();

        return movie;
    }
}
