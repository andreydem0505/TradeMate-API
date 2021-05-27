package com.herokuapp.trademateapi.demo.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.herokuapp.trademateapi.demo.DataEngine;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;

@Validated
public class NEObject {
    private String name;
    @Email(message = DataEngine.emailIncorrect)
    private String email;

    @JsonCreator
    public NEObject(@JsonProperty("name") String name,
                    @JsonProperty("email") String email) {
        this.name = name;
        this.email = email;
    }

    // getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
