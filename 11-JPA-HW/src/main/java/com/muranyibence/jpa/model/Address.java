package com.muranyibence.jpa.model;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Bence
 */
@Embeddable
public class Address implements Serializable{

    private Integer zipCode;
    private String city;
    private String country;
    private String street;
    private Integer number;

    public Address() {
        //empty
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}
