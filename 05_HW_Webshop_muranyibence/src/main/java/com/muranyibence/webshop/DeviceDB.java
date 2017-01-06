/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.webshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author Bence
 */
public class DeviceDB {

    private Map<String, DeviceEntity> devices = new HashMap<>();

    public DeviceEntity addDevice(DeviceEntity device) {
        String id = UUID.randomUUID().toString();
        device.setId(id);
        device.setCount(0);
        this.devices.put(device.getId(), device);
        return device;
    }

    public DeviceEntity getDevice(String id) {
        if (devices.get(id) == null) {
            throw new NoSuchElementException("There is no device with this id on the datatbase");
        }
        return devices.get(id);
    }

    public DeviceEntity editDevice(DeviceEntity device) {
        if (devices.get(device.getId()) == null) {
            throw new NoSuchElementException("There is no device with this id on the datatbase");
        }
        this.devices.put(device.getId(), device);
        return getDevice(device.getId());
    }

    public DeviceEntity deleteDevice(DeviceEntity device) {
        DeviceEntity deletedDevice = getDevice(device.getId());
        devices.remove(device.getId());
        return deletedDevice;
    }

    public List<DeviceEntity> getAllDevice() {
        List<DeviceEntity> deviceList = new ArrayList();
        deviceList.addAll(devices.values());
        return deviceList;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.devices);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DeviceDB other = (DeviceDB) obj;
        if (!Objects.equals(this.devices, other.devices)) {
            return false;
        }
        return true;
    }
    
    
}
