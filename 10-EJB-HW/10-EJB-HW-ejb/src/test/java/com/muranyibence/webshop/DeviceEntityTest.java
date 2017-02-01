package com.muranyibence.webshop;

/**
 *
 * @author Bence
 */
import com.muranyibence.webshop.entities.DeviceEntity;
import com.muranyibence.webshop.util.Manufacturer;
import com.muranyibence.webshop.util.Color;
import java.util.Set;
import java.util.UUID;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

//@author Adam 
public class DeviceEntityTest {

    private static ValidatorFactory vf;
    private static Validator validator;

    private DeviceEntity deviceEntity;

    @BeforeClass
    public static void init() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    @AfterClass
    public static void close() {
        vf.close();
    }

    @Test
    public void positiveTestAppleColor() {
        deviceEntity = new DeviceEntity(UUID.randomUUID().toString(), "Iphone 7", Manufacturer.APPLE, 0, Color.BLACK, 0);

        Set<ConstraintViolation<DeviceEntity>> violations = validator.validate(deviceEntity);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void negativeTestAppleColor() {
        deviceEntity = new DeviceEntity(UUID.randomUUID().toString(), "Iphone 7", Manufacturer.APPLE, 0, Color.RED, 0);

        Set<ConstraintViolation<DeviceEntity>> violations = validator.validate(deviceEntity);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("{DeviceColor.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void positiveTestSamsungColor() {
        deviceEntity = new DeviceEntity(UUID.randomUUID().toString(), "Galaxy S7", Manufacturer.SAMSUNG, 0, Color.BLACK, 0);

        Set<ConstraintViolation<DeviceEntity>> violations = validator.validate(deviceEntity);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void negativeTestSamsungColor() {
        deviceEntity = new DeviceEntity(UUID.randomUUID().toString(), "Galaxy S7", Manufacturer.SAMSUNG, 0, Color.GREEN, 0);

        Set<ConstraintViolation<DeviceEntity>> violations = validator.validate(deviceEntity);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("{DeviceColor.message}", violations.iterator().next().getMessageTemplate());
    }
}
