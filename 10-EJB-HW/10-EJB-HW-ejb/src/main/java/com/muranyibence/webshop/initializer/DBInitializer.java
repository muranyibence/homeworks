package com.muranyibence.webshop.initializer;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muranyibence.webshop.exeption.UserAlreadyInDBException;
import com.muranyibence.webshop.exeption.database.DeviceDB;
import com.muranyibence.webshop.exeption.database.UserDB;
import com.muranyibence.webshop.entities.DeviceEntity;
import com.muranyibence.webshop.entities.UserEntity;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author Bence
 */
@Singleton
@Startup
public class DBInitializer {

    private List<UserEntity> users;
    private List<DeviceEntity> devices;
    private JavaType userType;
    private JavaType deviceType;
    @Inject
    private UserDB userDB;
    @Inject
    private DeviceDB deviceDB;
    private static final Logger LOGGER = Logger.getLogger(DBInitializer.class.getName());
    private ObjectMapper mapper;
    private ClassLoader classLoader = getClass().getClassLoader();

    protected DBInitializer() {
        //empty
    }

    @PostConstruct
    public void init() {
        deviceDB.clearDB();
        userDB.clearDB();
        mapper = new ObjectMapper();
        userType = mapper.getTypeFactory().constructCollectionType(List.class, UserEntity.class);
        deviceType = mapper.getTypeFactory().constructCollectionType(List.class, DeviceEntity.class);

        try {
            readUserDBFromFile();
            readDeviceDBFromFile();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    private void readUserDBFromFile() {

        try {
            users = mapper.readValue(new File(classLoader.getResource("userDB.json").getFile()), userType);
            for (UserEntity user : users) {
                userDB.addUser(user);
            }
        } catch (IOException | UserAlreadyInDBException ex) {
            Logger.getLogger(DBInitializer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void readDeviceDBFromFile() {

        try {
            devices = mapper.readValue(new File(classLoader.getResource("deviceDB.json").getFile()), deviceType);
        } catch (IOException ex) {
            Logger.getLogger(DBInitializer.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (DeviceEntity device : devices) {
            deviceDB.addDevice(device);
        }
    }
}
