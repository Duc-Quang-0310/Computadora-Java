package com.example.computadora;

public class BuyHistoryItem {
    private String _id;
    private Boolean expand;
    private String imgUrl;
    private String status;
    private String date;
    private String hour;

    public BuyHistoryItem(String _id, Boolean expand, String imgUrl, String status, String date, String hour) {
        this._id = _id;
        this.expand = expand;
        this.imgUrl = imgUrl;
        this.status = status;
        this.date = date;
        this.hour = hour;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Boolean getExpand() {
        return expand;
    }

    public void setExpand(Boolean expand) {
        this.expand = expand;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "BuyHistoryItem{" +
                "_id='" + _id + '\'' +
                ", expand=" + expand +
                ", imgUrl='" + imgUrl + '\'' +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                ", hour='" + hour + '\'' +
                '}';
    }
}
