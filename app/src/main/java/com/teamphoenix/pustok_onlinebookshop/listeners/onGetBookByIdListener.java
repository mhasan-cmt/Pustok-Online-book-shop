package com.teamphoenix.pustok_onlinebookshop.listeners;

import com.teamphoenix.pustok_onlinebookshop.entity.Book;

public interface onGetBookByIdListener {
    void onSuccess(Book book);
    void onError(String errMsg);
}
