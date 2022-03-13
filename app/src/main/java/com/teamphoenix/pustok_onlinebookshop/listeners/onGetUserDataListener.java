package com.teamphoenix.pustok_onlinebookshop.listeners;

import com.teamphoenix.pustok_onlinebookshop.entity.User;

public interface onGetUserDataListener {
    public void onSuccess(User user);
    public void onError(String errMsg);
}
