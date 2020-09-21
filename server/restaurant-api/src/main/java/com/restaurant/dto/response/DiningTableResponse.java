package com.restaurant.dto.response;

public class DiningTableResponse {

    private Long id;

    private int numDiners;

    private String location;

    public DiningTableResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumDiners() {
        return numDiners;
    }

    public void setNumDiners(int numDiners) {
        this.numDiners = numDiners;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
