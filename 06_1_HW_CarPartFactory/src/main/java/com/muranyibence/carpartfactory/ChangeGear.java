/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.carpartfactory;

import com.muranyibence.carpartfactory.annotations.Defect;

/**
 *
 * @author Bence
 */
@Defect(author="Bence",message="Sometimes not flashing up")
public class ChangeGear extends Product {
    
    public ChangeGear(String id, CarType cartype) {
        super(id, cartype);
    }
    
}
