package com.teamphoenix.pustok_onlinebookshop.listeners;

import com.teamphoenix.pustok_onlinebookshop.entity.Writer;

import java.util.ArrayList;

public interface onGetAllWritersListener {
    void onSuccess(ArrayList<Writer> writers);
    void onFailed(String errMsg);
}
