package com.nguyendinhdoan.food.model;

public class Food {
    private String menu_id;
    private String name;
    private String image;
    private String description;
    private String discount;
    private String price;

    public Food() {
    }

    public Food(String menu_id, String name, String image, String description, String discount, String price) {
        this.menu_id = menu_id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.discount = discount;
        this.price = price;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
