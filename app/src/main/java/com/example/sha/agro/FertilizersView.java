package com.example.sha.agro;

public class FertilizersView {

    private String Name;
    private String Mobile;
    private String Location;
    private String Address;

    public FertilizersView(){}

    public FertilizersView(String name, String mobile, String location, String address) {
        this.Name = name;
        this.Mobile = mobile;
        this.Location = location;
        this.Address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        this.Mobile = mobile;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        this.Location = location;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }



}
