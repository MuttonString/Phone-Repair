package com.ccsu.servicetask.entity;

public class Factory {
    private int fac_id;
    private String fac_name;
    private String fac_phone;
    private String fac_item;
    private int fac_state;

    public Factory() {
    }

    public Factory(int fac_id, String fac_name, String fac_phone, String fac_item, int fac_state) {
        this.fac_id = fac_id;
        this.fac_name = fac_name;
        this.fac_phone = fac_phone;
        this.fac_item = fac_item;
        this.fac_state = fac_state;
    }

    public Factory(String fac_name, String fac_phone, String fac_item) {
        this.fac_name = fac_name;
        this.fac_phone = fac_phone;
        this.fac_item = fac_item;
    }

    public int getFac_id() {
        return fac_id;
    }

    public void setFac_id(int fac_id) {
        this.fac_id = fac_id;
    }

    public String getFac_name() {
        return fac_name;
    }

    public void setFac_name(String fac_name) {
        this.fac_name = fac_name;
    }

    public String getFac_phone() {
        return fac_phone;
    }

    public void setFac_phone(String fac_phone) {
        this.fac_phone = fac_phone;
    }

    public String getFac_item() {
        return fac_item;
    }

    public void setFac_item(String fac_item) {
        this.fac_item = fac_item;
    }

    public int getFac_state() {
        return fac_state;
    }

    public void setFac_state(int fac_state) {
        this.fac_state = fac_state;
    }

    @Override
    public String toString() {
        return "Factory{" +
                "fac_id=" + fac_id +
                ", fac_name='" + fac_name + '\'' +
                ", fac_phone='" + fac_phone + '\'' +
                ", fac_item='" + fac_item + '\'' +
                ", fac_state=" + fac_state +
                '}';
    }
}
