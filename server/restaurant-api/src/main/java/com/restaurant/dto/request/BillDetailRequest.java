package com.restaurant.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BillDetailRequest {

    @NotNull(message = "Cook must not be empty")
    private Long cookId;

    @NotBlank(message = "Client must not be empty")
    private String dish;

    @NotNull(message = "Client must not be empty")
    @Size(min = 0)
    private double price;

    public Long getCookId() {
        return cookId;
    }

    public void setCookId(Long cookId) {
        this.cookId = cookId;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
