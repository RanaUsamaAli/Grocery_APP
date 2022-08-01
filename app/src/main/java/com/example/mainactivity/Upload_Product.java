package com.example.mainactivity;

public class Upload_Product {

    private String Product_Name;
    private String Product_ID;
    private String Product_Des;
    private String Image_URL;
    private String Price_1;

    public Upload_Product(String product_Name, String product_ID, String product_Des,String Price , String image_URL) {
        if("".equals(Product_Name))
        {
            Product_Name="No_Name";
        }
        Product_Name = product_Name;
        Product_ID = product_ID;
        Product_Des = product_Des;
        Image_URL = image_URL;
        Price_1 = Price;
    }

    public String getPrice() {
        return Price_1;
    }

    public void setPrice(String price) {
        Price_1 = price;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public String getProduct_ID() {
        return Product_ID;
    }

    public String getProduct_Des() {
        return Product_Des;
    }

    public String getImage_URL() {
        return Image_URL;
    }
//    Setter Functions


    public void setProduct_Name(String product_Name) {
        Product_Name = product_Name;
    }

    public void setProduct_ID(String product_ID) {
        Product_ID = product_ID;
    }

    public void setProduct_Des(String product_Des) {
        Product_Des = product_Des;
    }

    public void setImage_URL(String image_URL) {
        Image_URL = image_URL;
    }
}
