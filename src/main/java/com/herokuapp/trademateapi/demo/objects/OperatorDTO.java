package com.herokuapp.trademateapi.demo.objects;

public class OperatorDTO {
    private String name;
    private String email;


    public OperatorDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // getters
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    // setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
}
