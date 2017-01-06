/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.carpartfactory.main;

import com.muranyibence.carpartfactory.CarFactory;
import com.muranyibence.carpartfactory.CarType;
import com.muranyibence.carpartfactory.ProductType;

/**
 *
 * @author Bence
 */
public class Main {

    private Main() {
        //empty
    }

    public static void main(String[] args) {

        CarFactory factory = new CarFactory();

        factory.createPart(ProductType.MIRROR, CarType.FORD);
        factory.createPart(ProductType.ELECTRICWINDOWLIFTER, CarType.MAZDA);
        factory.createPart(ProductType.TURNSIGNAL, CarType.AUDI);
        factory.createPart(ProductType.CHANGEGEAR, CarType.TOYOTA);
        
        factory.listDefectProducts();
    }

}
