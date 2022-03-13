package com.teamphoenix.pustok_onlinebookshop.entity;

public class owned_books {
    String user_id;
    String book_id;

    public owned_books(String user_id, String book_id) {
        this.user_id = user_id;
        this.book_id = book_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getBook_id() {
        return book_id;
    }
}
