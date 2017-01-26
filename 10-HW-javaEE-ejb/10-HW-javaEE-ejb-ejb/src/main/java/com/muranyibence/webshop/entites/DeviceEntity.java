package com.muranyibence.webshop.entites;

import com.muranyibence.webshop.util.Color;
import com.muranyibence.webshop.util.Manufacturer;
import com.muranyibence.webshop.annotations.Validate;
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
@Validate
public class DeviceEntity {

    @NotNull
    @Size(min = 36, max = 36)
    private String id;
    @NotNull
    @Size(min = 3)
    private String type;
    @NotNull
    private Manufacturer manufacturer;
    @NotNull
    @DecimalMin("0")
    private int price;
    @NotNull
    private Color color;
    @NotNull
    @DecimalMin("0")
    private Integer count;

    public DeviceEntity(String id, String type, Manufacturer manufacturer, int price, Color color, Integer count) {
        this.id = id;
        this.type = type;
        this.manufacturer = manufacturer;
        this.price = price;
        this.color = color;
        this.count = count;
    }

    public DeviceEntity(DeviceEntity device) {
        this.id = device.id;
        this.type = device.type;
        this.manufacturer = device.manufacturer;
        this.price = device.price;
        this.color = device.color;
        this.count = device.count;
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
