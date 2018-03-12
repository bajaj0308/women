package com.example.meghabajaj.womanhackathon;

/**
 * Created by MEGHA BAJAJ on 03-03-2018.
 */
public class User {

    public  String email;
    public String name;
    public String phone;
    public User() {
    }

    public User(String name, String phone, String email) {

        this.email = email;
        this.name = name;
        this.phone = phone;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



}
