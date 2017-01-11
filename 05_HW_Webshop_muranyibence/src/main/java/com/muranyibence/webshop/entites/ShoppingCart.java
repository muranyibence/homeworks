package com.muranyibence.webshop.entites;

import com.muranyibence.webshop.database.DeviceDB;
import com.muranyibence.webshop.exceptions.NotEnoughCountInDBException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bence
 */
public class ShoppingCart {

    private static final Logger LOGGER = Logger.getLogger(ShoppingCart.class.getName());
    private Map<String, DeviceEntity> devicesInCart;
    private int cost;

    public ShoppingCart() {
        devicesInCart = new HashMap<>();
        cost = 0;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void addDeviceToChart(String id, int count) {
        DeviceDB deviceDB = DeviceDB.getInstance();
        try {
            DeviceEntity device = deviceDB.takeDevice(id, count);
            if (devicesInCart.containsKey(id)) {
                devicesInCart.get(id).setCount(devicesInCart.get(id).getCount() + count);
                setCost(cost + (device.getPrice() * count));
            } else {
                devicesInCart.put(device.getId(), device);
                setCost(cost + (device.getPrice() * count));
            }
        } catch (NotEnoughCountInDBException ex) {
            LOGGER.info((Supplier<String>) ex);
        }

    }

    public void deleteDeviceFromChart(String id, int count) {
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

    public void deleteChart() {

        HashMap<String, DeviceEntity> selects = new HashMap<>();

        for (Map.Entry<String, DeviceEntity> entry : selects.entrySet()) {
            String key = entry.getKey();
            deleteDeviceFromChart(devicesInCart.get(key).getId(), devicesInCart.get(key).getCount());
        }

    }

    public void buyChart() {
        devicesInCart.keySet().forEach(currentKey -> {
            DeviceEntity device = devicesInCart.get(currentKey);
            LOGGER.log(Level.INFO, "Buyed: " + currentKey + " Count: " + device.getCount() + " Price: " + (device.getCount() * device.getPrice()));
        });
        LOGGER.log(Level.INFO, "Final cost: " + getCost());
    }
}
