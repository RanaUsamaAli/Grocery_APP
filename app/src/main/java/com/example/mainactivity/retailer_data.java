package com.example.mainactivity;

public class retailer_data {

    String email_ret;
    String Name_ret ;
    String Mobile_ret;
    String Store_Name;

    public retailer_data(String email_ret, String name_ret, String mobile_ret, String store_Name) {
        this.email_ret = email_ret;
        Name_ret = name_ret;
        Mobile_ret = mobile_ret;
        Store_Name = store_Name;
    }

    ////getter
    public String getEmail_ret() {
        return email_ret;
    }

    public String getName_ret() {
        return Name_ret;
    }

    public String getMobile_ret() {
        return Mobile_ret;
    }

    public String getStore_Name() {
        return Store_Name;
    }

    //Setter

}
