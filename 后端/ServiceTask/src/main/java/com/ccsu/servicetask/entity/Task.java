package com.ccsu.servicetask.entity;

import java.time.LocalDateTime;

public class Task {
    private int task_id;
    private String cus_name;
    private String cus_phone;
    private String service_item;
    private String task_no;
    private LocalDateTime task_time;
    private int task_state;

    public Task() {
    }

    public Task(int task_id, String cus_name, String cus_phone, String service_item, String task_no, LocalDateTime task_time, int task_state) {
        this.task_id = task_id;
        this.cus_name = cus_name;
        this.cus_phone = cus_phone;
        this.service_item = service_item;
        this.task_no = task_no;
        this.task_time = task_time;
        this.task_state = task_state;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getCus_name() {
        return cus_name;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    public String getCus_phone() {
        return cus_phone;
    }

    public void setCus_phone(String cus_phone) {
        this.cus_phone = cus_phone;
    }

    public String getService_item() {
        return service_item;
    }

    public void setService_item(String service_item) {
        this.service_item = service_item;
    }

    public String getTask_no() {
        return task_no;
    }

    public void setTask_no(String task_no) {
        this.task_no = task_no;
    }

    public LocalDateTime getTask_time() {
        return task_time;
    }

    public void setTask_time(LocalDateTime task_time) {
        this.task_time = task_time;
    }

    public int getTask_state() {
        return task_state;
    }

    public void setTask_state(int task_state) {
        this.task_state = task_state;
    }

    @Override
    public String toString() {
        return "Task{" +
                "task_id=" + task_id +
                ", cus_name='" + cus_name + '\'' +
                ", cus_phone='" + cus_phone + '\'' +
                ", service_item='" + service_item + '\'' +
                ", task_no='" + task_no + '\'' +
                ", task_time=" + task_time +
                ", task_state=" + task_state +
                '}';
    }
}
