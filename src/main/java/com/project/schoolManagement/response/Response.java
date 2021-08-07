package com.project.schoolManagement.response;

public class Response {

    private Object data;
    private Boolean success;
    private String error;

    public Response() {
    }

    public Response(Object data, Boolean success, String error) {
        this.data = data;
        this.success = success;
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
