package com.herokuapp.trademateapi.demo.objects;

import java.util.List;

public class RequestsListObject extends MessageObject {
    private int total;
    private List<RequestNameObject> requests;

    public RequestsListObject(String message) {
        super(message);
    }

    public RequestsListObject(String message, List<RequestNameObject> requests) {
        super(message);
        this.requests = requests;
        total = requests.size();
    }

    // getters
    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public int getTotal() {
        return total;
    }

    public List<RequestNameObject> getRequests() {
        return requests;
    }

    //setters
    @Override
    public void setMessage(String message) {
        super.setMessage(message);
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setRequests(List<RequestNameObject> requests) {
        this.requests = requests;
    }
}
