package com.teamphoenix.pustok_onlinebookshop.entity;

public class Cart {
    String cart_id, user_id, currentTime, currentDate, book_id, totalPrice, totalQuantity;

    public Cart(String user_id, String currentTime, String book_id, String currentDate, String totalPrice, String totalQuantity) {
        this.user_id = user_id;
        this.currentTime = currentTime;
        this.currentDate = currentDate;
        this.book_id = book_id;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
    }

    public String getCart_id() {
        return cart_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public String getBook_id() {
        return book_id;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public Cart() {
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cart_id='" + cart_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", currentTime='" + currentTime + '\'' +
                ", currentDate='" + currentDate + '\'' +
                ", book_id='" + book_id + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                ", totalQuantity='" + totalQuantity + '\'' +
                '}';
    }
}
