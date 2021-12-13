package com.example.computadora;

public class displayBlog {
    private String title;
    private String image;

    public displayBlog(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "displayBlog{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
