package com.herokuapp.trademateapi.demo.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class RequestNameObject {
    private String subject;
    private String text;
    private String operatorName;
    private LocalDateTime dateTime;

    @JsonCreator
    public RequestNameObject(@JsonProperty("subject") String subject,
                              @JsonProperty("text") String text,
                              @JsonProperty("operatorName") String operatorName,
                              @JsonProperty("dateTime") LocalDateTime dateTime) {
        this.subject = subject;
        this.text = text;
        this.operatorName = operatorName;
        this.dateTime = dateTime;
    }

    // getters
    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public String getOperatorName() {
        return operatorName;
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

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
