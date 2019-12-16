package com.example.sha.agro;

public class EquipmentsView
{

    private String Name;
    private String Mobile;
    private String Location;
    private String Address;
    private String Image;
    public EquipmentsView() {
    }

    public EquipmentsView(String name, String mobile, String location, String address, String  image) {
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

    public String getMobile() {
        return Mobile;
    }

    public String getLocation() {
        return Location;
    }

    public String getAddress() {
        return Address;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setAddress(String address) {
        Address = address;
    }

}