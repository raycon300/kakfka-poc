package com.example.models;

import java.util.UUID;

public class Movie {

    private String name;
    private String id;

    public Movie(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }

    public Movie() {
    }

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
}
