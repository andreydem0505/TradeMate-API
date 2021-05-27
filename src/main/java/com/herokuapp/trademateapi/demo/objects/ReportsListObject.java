package com.herokuapp.trademateapi.demo.objects;


public class ReportsListObject extends MessageObject {
    private int total;
    private String[] reports;

    public ReportsListObject(String message) {
        super(message);
    }

    public ReportsListObject(String message, String[] reports) {
        super(message);
        this.reports = reports;
        total = reports.length;
    }

    // getters
    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public int getTotal() {
        return total;
    }

    public String[] getReports() {
        return reports;
    }

    // setters
    @Override
    public void setMessage(String message) {
        super.setMessage(message);
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setReports(String[] reports) {
        this.reports = reports;
    }
}
