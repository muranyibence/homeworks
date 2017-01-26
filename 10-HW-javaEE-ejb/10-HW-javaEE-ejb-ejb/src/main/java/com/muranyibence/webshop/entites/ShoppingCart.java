package com.muranyibence.webshop.entites;

import com.muranyibence.webshop.database.DeviceDB;
import com.muranyibence.webshop.exceptions.DeviceNotExistInDBException;
import com.muranyibence.webshop.exceptions.NotEnoughCountInDBException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;

/**
 *
 * @author Bence
 */
@Stateful
@StatefulTimeout(value = 5L, unit = TimeUnit.MINUTES)
public class ShoppingCart {

    private static final Logger LOGGER = Logger.getLogger(ShoppingCart.class.getName());
    private final Map<String, DeviceEntity> devicesInCart;
    private int cost;

    public ShoppingCart() {
        devicesInCart = new HashMap<>();
        cost = 0;
    }

    public int getCost() {
        return cost;
    }

    private void setCost(int cost) {
        this.cost = cost;
    }

    public void addDeviceToChart(String id, int count) throws NotEnoughCountInDBException, DeviceNotExistInDBException {
        DeviceDB deviceDB = DeviceDB.getInstance();

        DeviceEntity device = deviceDB.takeDevice(id, count);
        if (devicesInCart.containsKey(id)) {
            devicesInCart.get(id).setCount(devicesInCart.get(id).getCount() + count);
            setCost(cost + (device.getPrice() * count));
        } else {
            devicesInCart.put(device.getId(), device);
            setCost(cost + (device.getPrice() * count));
        }
    }

    public void deleteDeviceFromChart(String id, int count) throws DeviceNotExistInDBException {
        DeviceDB deviceDB = DeviceDB.getInstance();
        if (devicesInCart.containsKey(id)) {
            deviceDB.addExistingDevice(id, count);
            setCost(cost - ((deviceDB.getDevice(id).getPrice()) * count));
            if (count == devicesInCart.get(id).getCount()) {
                devicesInCart.remove(id);
            } else {
                devicesInCart.get(id).setCount(devicesInCart.get(id).getCount() - count);
            }
        }
    }

    @Remove
    public void deleteChart() throws DeviceNotExistInDBException {

        HashMap<String, DeviceEntity> selects = new HashMap<>();

        for (Map.Entry<String, DeviceEntity> entry : selects.entrySet()) {
            String key = entry.getKey();
            deleteDeviceFromChart(devicesInCart.get(key).getId(), devicesInCart.get(key).getCount());
        }

    }

    @Remove
    public List<DeviceEntity> buyChart() {
        devicesInCart.keySet().forEach(currentKey -> {
            DeviceEntity device = devicesInCart.get(currentKey);
            LOGGER.log(Level.INFO, "Buyed: " + currentKey + " Count: " + device.getCount() + " Price: " + (device.getCount() * device.getPrice()));
        });
        LOGGER.log(Level.INFO, "Final cost: " + getCost());
        
    List<DeviceEntity> boughtDevices = new ArrayList<DeviceEntity>(devicesInCart.values());
    return boughtDevices;
    }

}
