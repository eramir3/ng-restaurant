package com.restaurant.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DiningTableRequest {

    private Long id;

    @NotNull(message = "Number of diners must not be empty")
    private int numDiners;

    @Size(max = 20, message = "location must have a maximum of 20 characters")
    private String location;

    public DiningTableRequest() {
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
