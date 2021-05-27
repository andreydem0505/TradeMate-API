package com.herokuapp.trademateapi.demo.objects;

public class ResultOfRegistrationObject extends MessageObject {
    private String accessToken;

    public ResultOfRegistrationObject(String message) {
        super(message);
    }

    public ResultOfRegistrationObject(String message, String accessToken) {
        super(message);
        this.accessToken = accessToken;
    }

    // getters
    public String getMessage() {
        return super.getMessage();
    }

    public String getAccessToken() {
        return accessToken;
    }

    // setters
    public void setMessage(String message) {
        super.setMessage(message);
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
