package com.minakov.database.entity;
/**
 * This class describes a product form DB.
 *
 * @author Serhii Minakov
 */
public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private int amountOnStorage;

    public Product(int id, String name, double price, int amountOnStorage) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amountOnStorage = amountOnStorage;
    }

    public Product(int id, String name, double price, int amountOnStorage, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amountOnStorage = amountOnStorage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmountOnStorage() {
        return amountOnStorage;
    }

    public void setAmountOnStorage(int amountOnStorage) {
        this.amountOnStorage = amountOnStorage;
    }
}
