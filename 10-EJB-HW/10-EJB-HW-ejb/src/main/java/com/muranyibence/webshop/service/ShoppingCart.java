package com.muranyibence.webshop.service;

import com.muranyibence.webshop.database.DeviceDB;
import com.muranyibence.webshop.entities.DeviceEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

import javax.inject.Inject;

/**
 *
 * @author Bence
 */
@Stateful
@SessionScoped
public class ShoppingCart implements Serializable {

    @Inject
    private DeviceDB deviceDB;

    private Map<String, DeviceEntity> devicesInCart;
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

    public void addDeviceToChart(String id, int count) {
        DeviceEntity device = deviceDB.takeDevice(id, count);
        if (devicesInCart.containsKey(id)) {
            devicesInCart.get(id).setCount(devicesInCart.get(id).getCount() + count);
            setCost(cost + (device.getPrice() * count));
        } else {
            devicesInCart.put(device.getId(), device);
            setCost(getCost() + (device.getPrice() * count));

        }
    }

    public void deleteDeviceFromChart(String id, int count) {
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
    public void deleteChart() {

        HashMap<String, DeviceEntity> selects = new HashMap<>();

        for (Map.Entry<String, DeviceEntity> entry : selects.entrySet()) {
            String key = entry.getKey();
            deleteDeviceFromChart(devicesInCart.get(key).getId(), devicesInCart.get(key).getCount());

        }
        setCost(0);

    }

    public List<DeviceEntity> buyChart() {
        return new ArrayList<DeviceEntity>(devicesInCart.values());
    }

}
