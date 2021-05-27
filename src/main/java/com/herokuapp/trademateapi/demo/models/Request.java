package com.herokuapp.trademateapi.demo.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Request {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "request_seq")
    private Long id;

    @Column
    private String subject;

    @Column
    private String text;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "merchandiser_id", nullable = false)
    private Merchandiser merchandiser;

    @ManyToOne
    @JoinColumn(name = "operator_id", nullable = false)
    private Operator operator;

    public Request() {}

    public Request(String subject, String text, LocalDateTime dateTime, Merchandiser merchandiser, Operator operator) {
        this.subject = subject;
        this.text = text;
        this.dateTime = dateTime;
        this.merchandiser = merchandiser;
        this.operator = operator;
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Merchandiser getMerchandiser() {
        return merchandiser;
    }

    public Operator getOperator() {
        return operator;
    }

    // setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setMerchandiser(Merchandiser merchandiser) {
        this.merchandiser = merchandiser;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }
}
