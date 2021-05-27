package com.herokuapp.trademateapi.demo.objects;

public class PhotoDto {
    private long id;
    private byte[] bytecode;

    public PhotoDto(long id, byte[] bytecode) {
        this.id = id;
        this.bytecode = bytecode;
    }

    // getters
    public long getId() {
        return id;
    }

    public byte[] getBytecode() {
        return bytecode;
    }

    // setters
    public void setId(long id) {
        this.id = id;
    }

    public void setBytecode(byte[] bytecode) {
        this.bytecode = bytecode;
    }
}
