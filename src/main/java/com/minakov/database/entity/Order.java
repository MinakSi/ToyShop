package com.minakov.database.entity;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private LocalDateTime date;
    private Status status;
    private User user;
    private int invoiceNumber;
    private int total;

    public Order(int id, LocalDateTime date, Status status, User user, int invoiceNumber, int total) {
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
