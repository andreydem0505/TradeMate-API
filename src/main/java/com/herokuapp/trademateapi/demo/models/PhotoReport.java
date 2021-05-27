package com.herokuapp.trademateapi.demo.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PhotoReport {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "photoReport_seq")
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "photoReport", cascade = CascadeType.REMOVE)
    private List<Photo> photos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "merchandiser_id", nullable = false)
    private Merchandiser merchandiser;

    public PhotoReport() {}

    public PhotoReport(String name, Merchandiser merchandiser) {
        this.name = name;
        this.merchandiser = merchandiser;
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public Merchandiser getMerchandiser() {
        return merchandiser;
    }

    // setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public void setMerchandiser(Merchandiser merchandiser) {
        this.merchandiser = merchandiser;
    }
}
