package com.muranyibence.webshop.database;

import com.muranyibence.webshop.exceptions.DeviceAlreadyInDBException;
import com.muranyibence.webshop.entites.DeviceEntity;
import com.muranyibence.webshop.exceptions.DeviceNotExistInDBException;
import com.muranyibence.webshop.exceptions.NotEnoughCountInDBException;
import com.muranyibence.webshop.interceptors.ValidatorInterceptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptors;

/**
 *
 * @author Bence
 */
@Interceptors(ValidatorInterceptor.class)
public class DeviceDB {

    private static DeviceDB instance = null;
    private static final String DEVICE = "Device ";
    private static final String NOT_EXIST=" doesnt exist in database";
    private Map<String, DeviceEntity> devices = new HashMap<>();


    protected DeviceDB() {
        // Exists only to defeat instantiation.
    }

    public static DeviceDB getInstance() {
        if (instance == null) {
            instance = new DeviceDB();
        }
        return instance;
    }

    @ExcludeClassInterceptors
    public DeviceEntity addDevice(DeviceEntity device) {
        if (devices.containsKey(device.getId())) {
            throw new DeviceAlreadyInDBException(new String(DEVICE + device.getId() + " is already in database"));
        }
        String id = UUID.randomUUID().toString();
        device.setId(id);
        device.setCount(0);
        this.devices.put(device.getId(), device);
        return device;
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
          devices.get(id).setCount(devices.get(id).getCount()+count);

    }

    public DeviceEntity takeDevice(String id, int count) throws NotEnoughCountInDBException {
        if (devices.get(id) == null) {
            throw new DeviceNotExistInDBException(new String(DEVICE + id + NOT_EXIST));
        } else if (devices.get(id).getCount() < count) {
            throw new NotEnoughCountInDBException(new String("There is only " + devices.get(id).getCount().toString() + id + " device in the database"));
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
