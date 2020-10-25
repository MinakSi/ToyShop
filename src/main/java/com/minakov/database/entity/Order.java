package com.minakov.database.entity;


import java.time.LocalDate;
import java.util.Comparator;


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

    public static Comparator<Order> orderIdDec = new Comparator<Order>() {
        @Override
        public int compare(Order o1, Order o2) {
            return o2.id - o1.id;
        }
    };
    public static Comparator<Order> orderStatus = new Comparator<Order>() {
        @Override
        public int compare(Order o1, Order o2) {
            return o1.status.getId() - o2.status.getId();
        }
    };

}
