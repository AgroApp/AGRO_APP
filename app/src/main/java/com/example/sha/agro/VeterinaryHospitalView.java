package com.example.sha.agro;

public class VeterinaryHospitalView  {


    private String Name;
    private String Mobile;
    private String Location;
    private String Address;
    private String Image;
    public VeterinaryHospitalView(){}

    public VeterinaryHospitalView(String name, String mobile, String location, String address, String  image) {
        this.Name = name;
        this.Mobile = mobile;
        this.Location = location;
        this.Address = address;
        this.Image = image;
    }
    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
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
