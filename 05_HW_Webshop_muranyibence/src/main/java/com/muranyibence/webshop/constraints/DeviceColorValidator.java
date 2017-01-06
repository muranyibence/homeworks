/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.webshop.constraints;

import com.muranyibence.webshop.Color;
import com.muranyibence.webshop.DeviceEntity;
import com.muranyibence.webshop.Manufacturer;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Bence
 */
public class DeviceColorValidator implements ConstraintValidator<DeviceColor, DeviceEntity> {

    @Override
    public void initialize(DeviceColor a) {
        //empty method
    }

    @Override
    public boolean isValid(DeviceEntity t, ConstraintValidatorContext cvc) {

        if (t.getManufacturer() == Manufacturer.APPLE && (t.getColor() != Color.WHITE && t.getColor() != Color.BLACK)) {
            return false;
        } else if (t.getManufacturer() == Manufacturer.SAMSUNG && t.getColor() == Color.GREEN) {
            return false;
        }
        return true;
    }
}
