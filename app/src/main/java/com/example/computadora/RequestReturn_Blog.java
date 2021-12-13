package com.example.computadora;

import java.util.ArrayList;

public class RequestReturn_Blog {
    private ArrayList<String> imgHeadline;
    private ArrayList<String> blog;
    private String _id;
    private String headline;

    public RequestReturn_Blog(ArrayList<String> imgHeadline, ArrayList<String> blog, String _id, String headline) {
        this.imgHeadline = imgHeadline;
        this.blog = blog;
        this._id = _id;
        this.headline = headline;
    }

    @Override
    public String toString() {
        return "RequestReturn_Blog{" +
                "imgHeadline=" + imgHeadline +
                ", blog=" + blog +
                ", _id='" + _id + '\'' +
                ", headline=" + headline +
                '}';
    }

    public ArrayList<String> getImgHeadline() {
        return imgHeadline;
    }

    public void setImgHeadline(ArrayList<String> imgHeadline) {
        this.imgHeadline = imgHeadline;
    }

    public ArrayList<String> getBlog() {
        return blog;
    }

    public void setBlog(ArrayList<String> blog) {
        this.blog = blog;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }
}
