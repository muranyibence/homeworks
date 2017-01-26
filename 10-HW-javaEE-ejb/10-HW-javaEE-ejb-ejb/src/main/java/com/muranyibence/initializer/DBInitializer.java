package com.muranyibence.initializer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muranyibence.webshop.database.DeviceDB;
import com.muranyibence.webshop.database.UserDB;
import com.muranyibence.webshop.entites.DeviceEntity;
import com.muranyibence.webshop.entites.UserEntity;
import com.muranyibence.webshop.exceptions.DeviceAlreadyInDBException;
import com.muranyibence.webshop.exceptions.UserAlreadyInDBException;
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

    private static List<UserEntity> users;
    private static List<DeviceEntity> devices;
    private static DBInitializer instance = null;
    @Inject
    private UserDB userDB;
    @Inject
    private DeviceDB deviceDB;
    private static final Logger logger = Logger.getLogger(DBInitializer.class.getName());

    protected DBInitializer() throws UserAlreadyInDBException, DeviceAlreadyInDBException {
    }

    public static DBInitializer getInstance() throws UserAlreadyInDBException, DeviceAlreadyInDBException {
        if (instance == null) {
            instance = new DBInitializer();
        }
        return instance;
    }

    @PostConstruct
    public void init() throws UserAlreadyInDBException, DeviceAlreadyInDBException {
        userDB = UserDB.getInstance();
        deviceDB = DeviceDB.getInstance();

        try {
            readDeviceDBFromFile();
            readUserDBFromFile();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    private void readUserDBFromFile() throws IOException, UserAlreadyInDBException {
        ObjectMapper mapper = new ObjectMapper();
        users = mapper.readValue(new File("userDB.json"), new TypeReference<List<UserEntity>>() {
        });
        for (UserEntity user : users) {
            userDB.addUser(user);
        }
    }

    private void readDeviceDBFromFile() throws IOException, DeviceAlreadyInDBException {

        ObjectMapper mapper = new ObjectMapper();
        devices = mapper.readValue(new File("deviceDB.json"), new TypeReference<List<DeviceEntity>>() {
        });
        for (DeviceEntity device : devices) {
            deviceDB.addDevice(device);
        }
    }

}
