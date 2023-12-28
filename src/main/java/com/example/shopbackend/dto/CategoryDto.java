package com.example.shopbackend.dto;

public class CategoryDto {
    private long id;
    private int quantity;
    public CategoryDto(
            long id, int quantity
    ) {
        this.id = id;
        this.quantity = quantity;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }
}
