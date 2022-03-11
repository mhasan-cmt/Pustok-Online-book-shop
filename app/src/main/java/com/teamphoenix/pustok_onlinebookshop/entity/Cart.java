package com.teamphoenix.pustok_onlinebookshop.entity;

public class Cart {
    String cart_id;
    String book_id;
    String user_id;

    public String getBook_id() {
        return book_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getCart_id() {
        return cart_id;
    }

    public Cart(String book_id, String user_id, String cart_id) {
        this.book_id = book_id;
        this.user_id = user_id;
        this.cart_id = cart_id;
    }
}
