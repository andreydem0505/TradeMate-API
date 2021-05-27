package com.herokuapp.trademateapi.demo.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.herokuapp.trademateapi.demo.DataEngine;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Validated
public class NPEObject {
    private String name;
    @Size(min = 8, message = DataEngine.passwordUnreliable)
    private String password;
    @Email(message = DataEngine.emailIncorrect)
    private String email;

    @JsonCreator
    public NPEObject(@JsonProperty("name") String name,
                     @JsonProperty("password") String password,
                     @JsonProperty("email") String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    // getters
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
