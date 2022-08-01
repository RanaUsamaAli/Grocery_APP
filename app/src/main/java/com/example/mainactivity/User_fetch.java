package com.example.mainactivity;
//Model class for User view
public class User_fetch {

    String image_URL;
    String price;
    String product_Des;
    String product_ID;
    String product_Name;

    User_fetch(){

    }

    public User_fetch(String image_URL, String price, String product_Des, String product_ID, String product_Name) {
        this.image_URL = image_URL;
        this.price = price;
        this.product_Des = product_Des;
        this.product_ID = product_ID;
        this.product_Name = product_Name;
    }

    public String getImage_URL() {
        return image_URL;
    }

    public void setImage_URL(String image_URL) {
        this.image_URL = image_URL;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProduct_Des() {
        return product_Des;
    }

    public void setProduct_Des(String product_Des) {
        this.product_Des = product_Des;
    }

    public String getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(String product_ID) {
        this.product_ID = product_ID;
    }

    public String getProduct_Name() {
        return product_Name;
    }

    public void setProduct_Name(String product_Name) {
        this.product_Name = product_Name;
    }
}
