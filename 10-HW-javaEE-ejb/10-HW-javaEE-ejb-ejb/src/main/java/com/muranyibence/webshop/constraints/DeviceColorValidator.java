package com.muranyibence.webshop.constraints;

import com.muranyibence.webshop.util.Color;
import com.muranyibence.webshop.entites.DeviceEntity;
import com.muranyibence.webshop.util.Manufacturer;
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
        boolean appleNotBlackOrWhite = device.getManufacturer() == Manufacturer.APPLE && !(device.getColor() == Color.WHITE || device.getColor() == Color.BLACK);
        boolean samsungGreen = device.getManufacturer() == Manufacturer.SAMSUNG && device.getColor() == Color.GREEN;
        if (appleNotBlackOrWhite || samsungGreen) {
            return false;
        }
        return true;

    }
}
