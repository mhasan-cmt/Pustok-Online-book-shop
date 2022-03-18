package com.teamphoenix.pustok_onlinebookshop.listeners;

import com.teamphoenix.pustok_onlinebookshop.entity.Publisher;

public interface onGetPublisherByIdListener {
    void onSuccess(Publisher publisher);
    void onError(String errorMsg);
}
