package com.minakov.database.entity;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private LocalDateTime date;
    private Status status;
    private User user;
    private int invoiceNumber;
    private int total;


}
