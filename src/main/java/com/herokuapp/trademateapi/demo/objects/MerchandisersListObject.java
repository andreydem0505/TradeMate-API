package com.herokuapp.trademateapi.demo.objects;

import java.util.List;

public class MerchandisersListObject extends MessageObject {
    private int total;
    private List<MerchandiserDTO> merchandisers;

    public MerchandisersListObject(String message) {
        super(message);
    }

    public MerchandisersListObject(String message, List<MerchandiserDTO> merchandisers) {
        super(message);
        this.merchandisers = merchandisers;
        total = merchandisers.size();
    }

    // getters
    public String getMessage() {
        return super.getMessage();
    }

    public int getTotal() {
        return total;
    }

    public List<MerchandiserDTO> getMerchandisers() {
        return merchandisers;
    }

    // setters
    public void setMessage(String message) {
        super.setMessage(message);
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setMerchandisers(List<MerchandiserDTO> merchandisers) {
        this.merchandisers = merchandisers;
    }
}
