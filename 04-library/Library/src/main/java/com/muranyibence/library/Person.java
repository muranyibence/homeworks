/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.library;

import java.sql.Date;

/**
 *
 * @author Bence
 */
public class Person {

    private String name;
    private Date born;
    private Gender gender;

    public enum Gender {
        MALE, FEMALE
    }

    public Person(String name, Date born, Gender gender) {
        this.name = name;
        this.born = born;
        this.gender = gender;

    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    public String getName() {
        return name;
    }

    public Date getBorn() {
        return born;
    }

}
