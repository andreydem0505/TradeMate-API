package com.herokuapp.trademateapi.demo.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Operator {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "supervisor_seq")
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "operator", cascade = CascadeType.REMOVE)
    private List<Request> requests = new ArrayList<>();

    public Operator() {}

    public Operator(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Company getCompany() {
        return company;
    }

    public List<Request> getRequests() {
        return requests;
    }

    // setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}
