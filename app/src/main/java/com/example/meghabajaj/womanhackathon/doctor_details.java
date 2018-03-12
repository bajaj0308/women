package com.example.meghabajaj.womanhackathon;

/**
 * Created by MEGHA BAJAJ on 04-03-2018.
 */

public class doctor_details {
    public String name;
    public String phone;
    public String street;
    public String state;
    public String pincode;
    public String type;

    public doctor_details() {
    }

    public doctor_details(String name, String phone, String state,String pincode,String type) {
        this.name = name;
        this.phone = phone;
        this.state=state;
        this.pincode=pincode;
        this.type=type;

    }
    public doctor_details(String name, String phone,String street,String state, String pincode,String type) {
        this.name = name;
        this.phone = phone;
        this.street=street;
        this.state=state;
        this.pincode=pincode;
        this.type=type;

    }
    public String getPincode(){
        return pincode;
    }
    public void setPincode(String pincode){
        this.pincode=pincode;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type=type;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
