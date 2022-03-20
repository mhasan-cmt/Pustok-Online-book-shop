package com.teamphoenix.pustok_onlinebookshop.entity;

import java.util.ArrayList;

public class owned_books {
    String user_id;
    ArrayList<String> book_id_list;

    public owned_books(String user_id, ArrayList<String> book_id_list) {
        this.user_id = user_id;
        this.book_id_list = book_id_list;
    }

    public String getUser_id() {
        return user_id;
    }

    public ArrayList<String> getBook_id_list() {
        return book_id_list;
    }
}
