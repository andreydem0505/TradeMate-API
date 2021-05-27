package com.herokuapp.trademateapi.demo.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class RequestEmailObject {
    private String subject;
    private String text;
    private String operatorEmail;
    private LocalDateTime dateTime;

    @JsonCreator
    public RequestEmailObject(@JsonProperty("subject") String subject,
                              @JsonProperty("text") String text,
                              @JsonProperty("operatorEmail") String operatorEmail,
                              @JsonProperty("dateTime") LocalDateTime dateTime) {
        this.subject = subject;
        this.text = text;
        this.operatorEmail = operatorEmail;
        this.dateTime = dateTime;
    }

    // getters
    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public String getOperatorEmail() {
        return operatorEmail;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    // setters
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOperatorEmail(String operatorEmail) {
        this.operatorEmail = operatorEmail;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
