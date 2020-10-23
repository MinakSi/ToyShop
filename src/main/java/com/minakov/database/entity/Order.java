package com.minakov.database.entity;


import java.time.LocalDate;


public class Order {
    private int id;
    private LocalDate date;
    private Status status;
    private User user;
    private String invoiceNumber;
    private double total;

    public Order(int id, LocalDate date, Status status, User user, String invoiceNumber, double total) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.user = user;
        this.invoiceNumber = invoiceNumber;
        this.total = total;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
