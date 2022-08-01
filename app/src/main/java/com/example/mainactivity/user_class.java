package com.example.mainactivity;

public class user_class {
    String email_ret;
    String Name_ret ;
    String Mobile_ret;

    public user_class(String email_ret, String name_ret, String mobile_ret) {
        this.email_ret = email_ret;
        Name_ret = name_ret;
        Mobile_ret = mobile_ret;
    }

    public String getEmail_ret() {
        return email_ret;
    }

    public String getName_ret() {
        return Name_ret;
    }

    public String getMobile_ret() {
        return Mobile_ret;
    }
}
