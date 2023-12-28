package com.example.shopbackend.dto;

public class ProductDto {
    private long id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private int totalView;
    private CategoryDto categoryDto;

    public ProductDto(
            long id, String name,
            String description, double price,
            int quantity, int totalView,
            CategoryDto categoryDto
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.totalView = totalView;
        this.categoryDto = categoryDto;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalView(int totalView) {
        this.totalView = totalView;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalView() {
        return totalView;
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }
}
