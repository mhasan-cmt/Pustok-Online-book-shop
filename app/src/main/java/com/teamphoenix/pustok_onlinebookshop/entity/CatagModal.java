package com.teamphoenix.pustok_onlinebookshop.entity;

public class CatagModal {
    String id, name, count, image;

    public CatagModal() {
    }


    public CatagModal(String name, String count, String image) {
        this.name = name;
        this.count = count;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public String getCount() {
        return count;
    }

    public String getImage() {
        return image;
    }
}
