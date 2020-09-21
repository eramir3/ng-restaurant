package com.restaurant.dto.response;

public class ErrorResponseContainer {

    private Object error;

    private String status;

    private int code;

    public ErrorResponseContainer(Object error, String status, int code) {
        this.error = error;
        this.status = status;
        this.code = code;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
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
