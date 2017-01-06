/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.webshop;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

/**
 *
 * @author Bence
 */
public class Main {

    private Main() {
        //empty constructor
    }

    public static void main(String[] args) throws IOException {
        readDeviceDBFromFile();
        readUserDBFromFile();

    }

    private static void readUserDBFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        UserDB userDB;
        List<UserEntity> users = mapper.readValue(new File("userDB.json"), new TypeReference<List<UserEntity>>() {
        });
        userDB = new UserDB();
        for (UserEntity user : users) {
            userDB.addUser(user);
        }
    }

    private static void readDeviceDBFromFile() throws IOException {
        DeviceDB deviceDB;
        ObjectMapper mapper = new ObjectMapper();
        List<DeviceEntity> devices = mapper.readValue(new File("deviceDB.json"), new TypeReference<List<DeviceEntity>>() {
        });
        deviceDB = new DeviceDB();
        for (DeviceEntity device : devices) {
            deviceDB.addDevice(device);
        }
    }
}
