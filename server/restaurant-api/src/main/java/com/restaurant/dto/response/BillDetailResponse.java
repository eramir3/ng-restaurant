package com.restaurant.dto.response;

import com.restaurant.entity.Bill;
import com.restaurant.entity.Cook;

public class BillDetailResponse {

    private Long id;

    private Cook cook;

    private String dish;

    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cook getCook() {
        return cook;
    }

    public void setCook(Cook cook) {
        this.cook = cook;
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
