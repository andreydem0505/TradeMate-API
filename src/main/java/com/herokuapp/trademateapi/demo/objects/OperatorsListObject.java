package com.herokuapp.trademateapi.demo.objects;

import java.util.List;

public class OperatorsListObject extends MessageObject {
    private int total;
    private List<OperatorDTO> operators;

    public OperatorsListObject(String message) {
        super(message);
    }

    public OperatorsListObject(String message, List<OperatorDTO> operators) {
        super(message);
        this.operators = operators;
        total = operators.size();
    }

    // getters
    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public int getTotal() {
        return total;
    }

    public List<OperatorDTO> getOperators() {
        return operators;
    }

    // setters
    @Override
    public void setMessage(String message) {
        super.setMessage(message);
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setMerchandisers(List<OperatorDTO> operators) {
        this.operators = operators;
    }
}
