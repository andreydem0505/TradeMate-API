package com.herokuapp.trademateapi.demo.objects;

public class EmailObject extends MessageObject {
    private String email;

    public EmailObject(String message) {
        super(message);
    }

    public EmailObject(String message, String email) {
        super(message);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
