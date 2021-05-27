package com.herokuapp.trademateapi.demo.models;

import javax.persistence.*;

@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "photo_seq")
    private Long id;

    @Column(length = 100000)
    private byte[] byteCode;

    @ManyToOne
    @JoinColumn(name = "photo_report_id", nullable = false)
    private PhotoReport photoReport;

    public Photo() {}

    public Photo(byte[] byteCode, PhotoReport photoReport) {
        this.byteCode = byteCode;
        this.photoReport = photoReport;
    }

    // getters
    public Long getId() {
        return id;
    }

    public byte[] getByteCode() {
        return byteCode;
    }

    public PhotoReport getPhotoReport() {
        return photoReport;
    }

    // setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setByteCode(byte[] byteCode) {
        this.byteCode = byteCode;
    }

    public void setPhotoReport(PhotoReport photoReport) {
        this.photoReport = photoReport;
    }
}
