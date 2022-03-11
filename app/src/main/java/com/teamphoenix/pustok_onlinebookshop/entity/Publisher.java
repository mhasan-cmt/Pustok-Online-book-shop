package com.teamphoenix.pustok_onlinebookshop.entity;

public class Publisher {
    String publisher_id,
    publisher_name,
    publisher_img,
    total_followers,
    total_books;

    public Publisher(String publisher_id, String publisher_name, String publisher_img, String total_followers, String total_books) {
        this.publisher_id = publisher_id;
        this.publisher_name = publisher_name;
        this.publisher_img = publisher_img;
        this.total_followers = total_followers;
        this.total_books = total_books;
    }

    public String getPublisher_id() {
        return publisher_id;
    }

    public String getPublisher_name() {
        return publisher_name;
    }

    public String getPublisher_img() {
        return publisher_img;
    }

    public String getTotal_followers() {
        return total_followers;
    }

    public String getTotal_books() {
        return total_books;
    }

    public void setPublisher_id(String publisher_id) {
        this.publisher_id = publisher_id;
    }

    public Publisher() {
    }

    public Publisher(String publisher_name, String publisher_img, String total_followers, String total_books) {
        this.publisher_name = publisher_name;
        this.publisher_img = publisher_img;
        this.total_followers = total_followers;
        this.total_books = total_books;
    }
}
