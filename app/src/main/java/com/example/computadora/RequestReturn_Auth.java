package com.example.computadora;

public class RequestReturn_Auth {
    private Boolean success;
    private String message;
    private RequestReturn_Login data;

    public RequestReturn_Auth(Boolean success, String message, RequestReturn_Login data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public RequestReturn_Auth(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RequestReturn_Login getData() {
        return data;
    }

    public void setData(RequestReturn_Login data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RequestReturn_Auth{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
