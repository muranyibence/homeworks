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
public class Member extends Person {

    int id;

    public Member(int id, String name, Date born, Gender gender) {
        super(name, born, gender);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    


    
}
