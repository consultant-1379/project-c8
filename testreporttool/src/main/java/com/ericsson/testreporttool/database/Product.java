package com.ericsson.testreporttool.database;

public class Product {

    private String name;
    private String number;

    public Product(){}

    public Product(String name, String cnaNumber) {
        this.name = name;
        this.number = cnaNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
