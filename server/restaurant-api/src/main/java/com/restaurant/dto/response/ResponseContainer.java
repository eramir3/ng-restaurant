package com.restaurant.dto.response;

public class ResponseContainer {

    private Object data;

    private String status;

    private int code;

    public ResponseContainer(Object data, String status, int code) {
        this.data = data;
        this.status = status;
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
