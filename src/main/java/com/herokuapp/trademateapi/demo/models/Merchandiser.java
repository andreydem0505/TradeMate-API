package com.herokuapp.trademateapi.demo.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Merchandiser {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "merchandiser_seq")
    private Long id;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String accessToken;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "merchandiser", cascade = CascadeType.REMOVE)
    private List<Request> requests = new ArrayList<>();

    @OneToMany(mappedBy = "merchandiser", cascade = CascadeType.REMOVE)
    private List<PhotoReport> photoReports = new ArrayList<>();

    public Merchandiser() {}

    public Merchandiser(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Company getCompany() {
        return company;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public List<PhotoReport> getPhotoReports() {
        return photoReports;
    }

    // setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public void setPhotoReports(List<PhotoReport> photoReports) {
        this.photoReports = photoReports;
    }
}
