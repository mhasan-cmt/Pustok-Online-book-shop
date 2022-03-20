package com.teamphoenix.pustok_onlinebookshop.entity;

public class Boookclass {
    String bname,wrname,price,img;


    public Boookclass() {
    }

    public Boookclass(String bname, String wrname, String price,String img) {
        this.bname = bname;
        this.wrname = wrname;
        this.price = price;
        this.img=img;
    }

    public String getBname() {
        return bname;
    }

    public String getWrname() {
        return wrname;
    }

    public String getPrice() {
        return price;
    }

    public String getImg() {
        return img;
    }
}
