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
    public boolean isValid(DeviceEntity device, ConstraintValidatorContext cvc) {
        boolean applenotblackorwhite = device.getManufacturer() == Manufacturer.APPLE && !(device.getColor() == Color.WHITE || device.getColor() == Color.BLACK);
        boolean samsunggreen = device.getManufacturer() == Manufacturer.SAMSUNG && device.getColor() == Color.GREEN;
        if (applenotblackorwhite || samsunggreen) {
            return false;
        }
        return true;

    }
}