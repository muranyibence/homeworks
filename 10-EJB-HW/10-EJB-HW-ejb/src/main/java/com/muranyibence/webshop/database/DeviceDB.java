package com.muranyibence.webshop.database;

import com.muranyibence.webshop.exeption.DeviceAlreadyInDBException;
import com.muranyibence.webshop.exeption.DeviceNotExistInDBException;
import com.muranyibence.webshop.exeption.NotEnoughCountInDBException;
import com.muranyibence.webshop.entities.DeviceEntity;
import com.muranyibence.webshop.interceptor.ValidatorInterceptor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.ejb.Singleton;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptors;

/**
 *
 * @author Bence
 */
@Interceptors(ValidatorInterceptor.class)
@Singleton
public class DeviceDB implements Serializable {

    private static final String DEVICE = "Device ";
    private static final String NOT_EXIST = " doesnt exist in database";
    private Map<String, DeviceEntity> devices = new HashMap<>();

    public DeviceDB() {
        // Exists only to defeat instantiation.
    }

    @ExcludeClassInterceptors
    public void clearDB() {
        devices = new HashMap<>();
    }

    @ExcludeClassInterceptors
    public DeviceEntity addDevice(DeviceEntity device) {
        if (null != devices.get(device.getId())) {
            throw new DeviceAlreadyInDBException("Error! Already stored item.");
        }
        device.setId(UUID.randomUUID().toString());
        devices.put(device.getId(), device);
        return devices.get(device.getId());
    }

    public DeviceEntity getDevice(String id) {
        if (devices.get(id) == null) {
            throw new DeviceNotExistInDBException(DEVICE + id + NOT_EXIST);
        }
        return devices.get(id);
    }

    public void addExistingDevice(String id, int count) {
        if (devices.get(id) == null) {
            throw new DeviceNotExistInDBException(DEVICE + id + NOT_EXIST);
        }
        devices.get(id).setCount(devices.get(id).getCount() + count);

    }

    public Map<String, DeviceEntity> getDevices() {
        return devices;
    }

    public void setDevices(Map<String, DeviceEntity> devices) {
        this.devices = devices;
    }

    @ExcludeClassInterceptors
    public DeviceEntity takeDevice(String id, int count) {
        if (devices.get(id) == null) {
            throw new DeviceNotExistInDBException(new String(DEVICE + id + NOT_EXIST));
        } else if (devices.get(id).getCount() < count) {
            throw new NotEnoughCountInDBException(new String("There is only " + devices.get(id).getCount() + " device in the database"));
        }
        DeviceEntity deviceToTake = devices.get(id);
        DeviceEntity entity = new DeviceEntity(deviceToTake);
        entity.setCount(count);
        deviceToTake.setCount(deviceToTake.getCount() - count);
        return entity;
    }

    public DeviceEntity editDevice(DeviceEntity device) {
        if (devices.get(device.getId()) == null) {
            throw new DeviceNotExistInDBException(new String(DEVICE + device.getId() + " doesnt exist in database"));
        }
        devices.put(device.getId(), device);
        return getDevice(device.getId());
    }

    public DeviceEntity deleteDevice(DeviceEntity device) {
        DeviceEntity deletedDevice = getDevice(device.getId());
        devices.remove(device.getId());
        return deletedDevice;
    }

    @ExcludeClassInterceptors
    public List<DeviceEntity> getAllDevice() {
        List<DeviceEntity> deviceList = new ArrayList();
        deviceList.addAll(devices.values());
        return deviceList;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(devices);
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
        if (!Objects.equals(devices, other.devices)) {
            return false;
        }
        return true;
    }

}
