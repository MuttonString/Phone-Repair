package com.ccsu.servicetask.entity;

public class Account {
    private int aid;
    private String admin_name;
    private String admin_pwd;
    private int admin_role;
    private int admin_state;

    public Account() {}

    public Account(String admin_name, String admin_pwd) {
        this.admin_name = admin_name;
        this.admin_pwd = admin_pwd;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_pwd() {
        return admin_pwd;
    }

    public void setAdmin_pwd(String admin_pwd) {
        this.admin_pwd = admin_pwd;
    }

    public int getAdmin_role() {
        return admin_role;
    }

    public void setAdmin_role(int admin_role) {
        this.admin_role = admin_role;
    }

    public int getAdmin_state() {
        return admin_state;
    }

    public void setAdmin_state(int admin_state) {
        this.admin_state = admin_state;
    }

    @Override
    public String toString() {
        return "Account{" +
                "aid=" + aid +
                ", admin_name='" + admin_name + '\'' +
                ", admin_pwd='" + admin_pwd + '\'' +
                ", admin_role=" + admin_role +
                ", admin_state=" + admin_state +
                '}';
    }
}
