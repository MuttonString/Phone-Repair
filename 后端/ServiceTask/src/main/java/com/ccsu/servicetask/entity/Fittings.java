package com.ccsu.servicetask.entity;

public class Fittings {
    private int fit_id;
    private String fit_name;
    private String fit_no;
    private int fit_qty;
    private String fit_factory;

    public Fittings() {
    }

    public Fittings(int fit_id, String fit_name, String fit_no, int fit_qty, String fit_factory) {
        this.fit_id = fit_id;
        this.fit_name = fit_name;
        this.fit_no = fit_no;
        this.fit_qty = fit_qty;
        this.fit_factory = fit_factory;
    }

    public int getFit_id() {
        return fit_id;
    }

    public void setFit_id(int fit_id) {
        this.fit_id = fit_id;
    }

    public String getFit_name() {
        return fit_name;
    }

    public void setFit_name(String fit_name) {
        this.fit_name = fit_name;
    }

    public String getFit_no() {
        return fit_no;
    }

    public void setFit_no(String fit_no) {
        this.fit_no = fit_no;
    }

    public int getFit_qty() {
        return fit_qty;
    }

    public void setFit_qty(int fit_qty) {
        this.fit_qty = fit_qty;
    }

    public String getFit_factory() {
        return fit_factory;
    }

    public void setFit_factory(String fit_factory) {
        this.fit_factory = fit_factory;
    }

    @Override
    public String toString() {
        return "FittingsServlet{" +
                "fit_id=" + fit_id +
                ", fit_name='" + fit_name + '\'' +
                ", fit_no='" + fit_no + '\'' +
                ", fit_qty=" + fit_qty +
                ", fit_factory='" + fit_factory + '\'' +
                '}';
    }
}
