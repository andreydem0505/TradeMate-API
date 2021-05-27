package com.herokuapp.trademateapi.demo.models;

import javax.persistence.*;

@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "shop_seq")
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    public Shop() {}

    public Shop(String name, Company company) {
        this.name = name;
        this.company = company;
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Company getCompany() {
        return company;
    }

    // setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
