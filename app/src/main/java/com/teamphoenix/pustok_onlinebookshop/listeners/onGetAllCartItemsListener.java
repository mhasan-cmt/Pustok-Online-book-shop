package com.teamphoenix.pustok_onlinebookshop.listeners;

import com.teamphoenix.pustok_onlinebookshop.entity.Cart;

import java.util.ArrayList;

public interface onGetAllCartItemsListener {
    void onSuccess(ArrayList<Cart> carts);
    void onError(String msg);
}
