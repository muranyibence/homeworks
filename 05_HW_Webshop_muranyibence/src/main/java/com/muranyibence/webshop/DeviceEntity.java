/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.webshop;

import com.muranyibence.webshop.constraints.DeviceColor;
import java.util.Objects;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Bence
 */
@DeviceColor
public class DeviceEntity {

    @NotNull
    @Size(min = 36, max = 36)
    String id;
    @NotNull
    @Size(min = 3)
    String type;
    @NotNull
    Manufacturer manufacturer;
    @NotNull
    @DecimalMin("0")
    int price;
    @NotNull
    Color color;
    @NotNull
    @DecimalMin("0")
    Integer count;

    public DeviceEntity(String id, String type, Manufacturer manufacturer, int price, Color color, Integer count) {
        this.id = id;
        this.type = type;
        this.manufacturer = manufacturer;
        this.price = price;
        this.color = color;
        this.count = count;
    }

    public DeviceEntity() {
        //empty constructor
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DeviceEntity other = (DeviceEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }    
}
