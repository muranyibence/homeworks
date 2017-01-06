/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.carpartfactory;


/**
 *
 * @author Bence
 */
public abstract class Product {

    private String id;
    private CarType cartype;

    public Product(String id, CarType cartype) {
        this.id = id;
        this.cartype = cartype;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CarType getCartype() {
        return cartype;
    }

    public void setCartype(CarType cartype) {
        this.cartype = cartype;
    }

    @Override
    public String toString() {
        return new String("Type: " + this.getClass().toString() + " Id: " + getId()+ " CarType: " +getCartype().toString());
    }

 
}
