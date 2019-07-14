package com.example.sha.agro;

public class PharmacyView {

    private String Name;
    private String Mobile;
    private String Location;
    private String Address;

    public PharmacyView(){}

    public PharmacyView(String name, String mobile, String location, String address) {
        this.Name = name;
        this.Mobile = mobile;
        this.Location = location;
        this.Address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
