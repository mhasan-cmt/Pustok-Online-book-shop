package com.teamphoenix.pustok_onlinebookshop.cart;

public class CartModel {
    int _id, image;
    String bookName, writerName,price, ratings;

    public CartModel() {
    }

    public CartModel(int _id, int image, String bookName, String writerName, String price, String ratings) {
        this._id = _id;
        this.image = image;
        this.bookName = bookName;
        this.writerName = writerName;
        this.price = price;
        this.ratings = ratings;
    }

    public CartModel(int image, String bookName, String writerName, String price, String ratings) {
        this.image = image;
        this.bookName = bookName;
        this.writerName = writerName;
        this.price = price;
        this.ratings = ratings;
    }

    public String getRatings() {
        return ratings;
    }

    public int get_id() {
        return _id;
    }

    public int getImage() {
        return image;
    }

    public String getBookName() {
        return bookName;
    }

    public String getWriterName() {
        return writerName;
    }

    public String getPrice() {
        return price;
    }
}
