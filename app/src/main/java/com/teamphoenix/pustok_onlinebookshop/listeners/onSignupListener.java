package com.teamphoenix.pustok_onlinebookshop.listeners;

import com.teamphoenix.pustok_onlinebookshop.entity.User;

public interface onSignupListener {
    void onSuccess(User user);
    void onError(String msg);
}
