/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.carpartfactory;

import com.muranyibence.carpartfactory.annotations.Defect;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bence
 */
public class CarFactory {

    private static final Logger LOGGER = Logger.getLogger(CarFactory.class.getName());
    private static final String PRODUCT = "Product: ";
    private static final String DATE = "Date: ";
    private List<Product> products;

    public CarFactory() {
        this.products = new ArrayList<>();
    }

    public void listDefectProducts() {
        List<Class> classes = new ArrayList<>();
        classes.add(ChangeGear.class);
        classes.add(Mirror.class);
        classes.add(ElectricWindowLifter.class);
        classes.add(TurnSignal.class);
        List<Class> defectProducts = new ArrayList<>();
        for (final Class clazz : classes) {
            if (clazz.getAnnotation(Defect.class) != null) {
                defectProducts.add(clazz);
            }
        }
        for (Product p : products) {
            if (defectProducts.contains(p.getClass())) {
                LOGGER.log(Level.INFO, p.toString());
            }
        }
    }

    public void createPart(ProductType producttype, CarType cartype) {

        switch (producttype) {

            case MIRROR:
                Mirror mirror = new Mirror(UUID.randomUUID().toString(), cartype);
                products.add(mirror);
                LOGGER.log(Level.INFO, PRODUCT + mirror.toString() + DATE + new Date());
                break;
            case CHANGEGEAR:
                ChangeGear changegear = new ChangeGear(UUID.randomUUID().toString(), cartype);
                products.add(changegear);
                LOGGER.log(Level.INFO, PRODUCT + changegear.toString() + DATE + new Date());
                break;

            case TURNSIGNAL:
                TurnSignal turnsignal = new TurnSignal(UUID.randomUUID().toString(), cartype);
                products.add(turnsignal);
                LOGGER.log(Level.INFO, PRODUCT + turnsignal.toString() + DATE + new Date());
                break;
            case ELECTRICWINDOWLIFTER:
                ElectricWindowLifter electricwindowlifter = new ElectricWindowLifter(UUID.randomUUID().toString(), cartype);
                products.add(electricwindowlifter);
                LOGGER.log(Level.INFO, PRODUCT + electricwindowlifter.toString() + DATE + new Date());
                break;
            default:
                break;
        }
    }
}
