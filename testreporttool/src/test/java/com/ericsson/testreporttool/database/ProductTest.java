package com.ericsson.testreporttool.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    Product data = new Product("CNA Test","CNA 1234");

    @Test
    void testConstructor() {
        Product newData = new Product("CNA XPT 100","CNA 2345");
    }

    @Test
    void getAndSetNumber() {
        String newNumber = "New Test Num";
        data.setNumber(newNumber);
        assertEquals(newNumber,data.getNumber());
    }

    @Test
    void getAndSetName() {
        String newName = "newCNA name";
        data.setName(newName);
        assertEquals(newName,data.getName());
    }

    @Test
    void testString(){
        assertEquals("Product{" +

                "name='" + data.getName() + '\'' +

                ", number='" + data.getNumber() + '\'' +

                '}', data.toString());
    }
}