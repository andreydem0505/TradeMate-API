package com.herokuapp.trademateapi.demo.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "company_seq")
    private Long id;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String accessToken;

    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private List<Merchandiser> merchandisers = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private List<Operator> operators = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private List<Shop> shops = new ArrayList<>();

    public Company() {}

    public Company(String name, String password, String email) {
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

    public List<Merchandiser> getMerchandisers() {
        return merchandisers;
    }

    public List<Operator> getOperators() {
        return operators;
    }

    public List<Shop> getShops() {
        return shops;
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

    public void setMerchandisers(List<Merchandiser> merchandisers) {
        this.merchandisers = merchandisers;
    }

    public void setOperators(List<Operator> operators) {
        this.operators = operators;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }
}
