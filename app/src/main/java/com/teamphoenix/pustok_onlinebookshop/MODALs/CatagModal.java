package com.teamphoenix.pustok_onlinebookshop.MODALs;

public class CatagModal {
    String  name,count,image;

    public CatagModal() {
    }




    public CatagModal(String name, String count,String image) {
        this.name = name;
        this.count = count;
        this.image= image;
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
