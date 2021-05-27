package com.herokuapp.trademateapi.demo.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PhotoObject {
    private byte[] byteCode;

    @JsonCreator
    public PhotoObject(@JsonProperty("byteCode") byte[] byteCode) {
        this.byteCode = byteCode;
    }

    public byte[] getByteCode() {
        return byteCode;
    }

    public void setByteCode(byte[] byteCode) {
        this.byteCode = byteCode;
    }
}
