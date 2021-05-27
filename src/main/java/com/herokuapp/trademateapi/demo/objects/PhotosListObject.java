package com.herokuapp.trademateapi.demo.objects;

import java.util.List;

public class PhotosListObject extends MessageObject {
    private int total;
    private List<PhotoDto> photos;

    public PhotosListObject(String message) {
        super(message);
    }

    public PhotosListObject(String message, List<PhotoDto> photos) {
        super(message);
        this.photos = photos;
        total = photos.size();
    }

    // getters
    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public int getTotal() {
        return total;
    }

    public List<PhotoDto> getPhotos() {
        return photos;
    }

    // setters
    @Override
    public void setMessage(String message) {
        super.setMessage(message);
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setPhotos(List<PhotoDto> photos) {
        this.photos = photos;
    }
}
