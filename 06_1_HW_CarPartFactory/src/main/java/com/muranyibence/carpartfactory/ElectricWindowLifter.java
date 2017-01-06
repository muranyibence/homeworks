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

@Defect(author="Bence",message="Sometimes just stucks")
public class ElectricWindowLifter extends Product{
    
    public ElectricWindowLifter(String id, CarType cartype) {
        super(id, cartype);
    }
    
}
