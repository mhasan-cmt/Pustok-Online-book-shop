package com.teamphoenix.pustok_onlinebookshop.entity;

public class Category {
    String category_id,
            category_name,
            category_img;

    public Category(String category_name, String category_img) {
        this.category_name = category_name;
        this.category_img = category_img;
    }

    public Category(String category_id, String category_name, String category_img) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.category_img = category_img;
    }

    public String getCategory_id() {
        return category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public String getCategory_img() {
        return category_img;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }
}
