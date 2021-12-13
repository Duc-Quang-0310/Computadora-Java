package com.example.computadora;

import java.util.Date;

public class RequestReturn_User {
    private Date date;
    private String email;
    private String imageUrl;
    private Boolean isAdmin;
    private Boolean isConfirmed;
    private String mobilePhone;
    private String name;
    private String password;
    private String shipCity;
    private String shipDistrict;
    private String shipSubDistrict;
    private String username;
    private String _id;

    public RequestReturn_User(Date date, String email, String imageUrl, Boolean isAdmin, Boolean isConfirmed, String mobilePhone, String name, String password, String shipCity, String shipDistrict, String shipSubDistrict, String username, String _id) {
        this.date = date;
        this.email = email;
        this.imageUrl = imageUrl;
        this.isAdmin = isAdmin;
        this.isConfirmed = isConfirmed;
        this.mobilePhone = mobilePhone;
        this.name = name;
        this.password = password;
        this.shipCity = shipCity;
        this.shipDistrict = shipDistrict;
        this.shipSubDistrict = shipSubDistrict;
        this.username = username;
        this._id = _id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        isConfirmed = confirmed;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipDistrict() {
        return shipDistrict;
    }

    public void setShipDistrict(String shipDistrict) {
        this.shipDistrict = shipDistrict;
    }

    public String getShipSubDistrict() {
        return shipSubDistrict;
    }

    public void setShipSubDistrict(String shipSubDistrict) {
        this.shipSubDistrict = shipSubDistrict;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "RequestReturn_User{" +
                "date=" + date +
                ", email='" + email + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", isAdmin=" + isAdmin +
                ", isConfirmed=" + isConfirmed +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", shipCity='" + shipCity + '\'' +
                ", shipDistrict='" + shipDistrict + '\'' +
                ", shipSubDistrict='" + shipSubDistrict + '\'' +
                ", username='" + username + '\'' +
                ", _id='" + _id + '\'' +
                '}';
    }
}
