package com.herokuapp.trademateapi.demo.objects;

public class ResultOfAuthObject extends MessageObject {
    private String name;
    private String accessToken;

    public ResultOfAuthObject(String message) {
        super(message);
    }

    public ResultOfAuthObject(String message, String name, String accessToken) {
        super(message);
        this.name = name;
        this.accessToken = accessToken;
    }

    // getters
    public String getMessage() {
        return super.getMessage();
    }

    public String getName() {
        return name;
    }

    public String getAccessToken() {
        return accessToken;
    }

    // setters
    public void setMessage(String message) {
        super.setMessage(message);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
