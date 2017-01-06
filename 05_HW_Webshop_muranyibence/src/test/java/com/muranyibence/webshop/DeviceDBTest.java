/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.webshop;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import javax.validation.Validation;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Bence
 */
public class DeviceDBTest {

    private DeviceDB deviceDB;
    private DeviceEntity deviceEntity;
    private DeviceEntity deviceEntity2;
    private static String id;

    public DeviceDBTest() {

    }

    @BeforeClass
    public static void init() {
        id = UUID.randomUUID().toString();
    }

    @Test
    public void addDevice() {

        deviceDB = new DeviceDB();
        deviceEntity = new DeviceEntity(UUID.randomUUID().toString(), "Iphone 7", Manufacturer.APPLE, 0, Color.BLACK, 0);
        DeviceEntity addedDevice = deviceDB.addDevice(deviceEntity);
        Boolean isAddedDevice = deviceDB.getDevice(addedDevice.getId()) != null;
        Assert.assertTrue(isAddedDevice);
    }

    @Test
    public void editDevice() {

        deviceDB = new DeviceDB();

        deviceEntity = new DeviceEntity(id, "Iphone 7", Manufacturer.APPLE, 0, Color.BLACK, 0);
        deviceDB.addDevice(deviceEntity);
        deviceEntity.setType("Iphone 6");
        DeviceEntity device1 = deviceDB.editDevice(deviceEntity);
        Assert.assertEquals("Iphone 6", device1.getType());
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteDevice() {

        deviceDB = new DeviceDB();
        deviceEntity = new DeviceEntity(id, "Iphone 7", Manufacturer.APPLE, 0, Color.BLACK, 0);
        DeviceEntity addedDevice = deviceDB.addDevice(deviceEntity);
        DeviceEntity deletedDevice = deviceDB.deleteDevice(addedDevice);
        Assert.assertEquals(1, deviceDB.getDevice(deletedDevice.getId()));
    }

    @Test
    public void getAllDevice() {

        deviceDB = new DeviceDB();
        deviceEntity = new DeviceEntity(id, "Iphone 7", Manufacturer.APPLE, 0, Color.BLACK, 0);
        deviceEntity2 = new DeviceEntity(UUID.randomUUID().toString(), "Iphone 8", Manufacturer.APPLE, 0, Color.BLACK, 0);
        deviceDB.addDevice(deviceEntity);
        deviceDB.addDevice(deviceEntity2);
        List<DeviceEntity> devices = deviceDB.getAllDevice();
        Assert.assertEquals(2, devices.size());
    }

}
