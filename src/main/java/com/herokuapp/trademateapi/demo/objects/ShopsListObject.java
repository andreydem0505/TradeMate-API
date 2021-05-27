package com.herokuapp.trademateapi.demo.objects;


public class ShopsListObject extends MessageObject {
    private int total;
    private String[] shops;

    public ShopsListObject(String message) {
        super(message);
    }

    public ShopsListObject(String message, String[] shops) {
        super(message);
        this.shops = shops;
        total = shops.length;
    }

    // getters
    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public int getTotal() {
        return total;
    }

    public String[] getShops() {
        return shops;
    }

    // setters
    @Override
    public void setMessage(String message) {
        super.setMessage(message);
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setShops(String[] shops) {
        this.shops = shops;
    }
}
