package com.muranyibence.webshop;

import com.muranyibence.webshop.entites.DeviceEntity;
import com.muranyibence.webshop.entites.UserEntity;
import com.muranyibence.webshop.database.DeviceDB;
import com.muranyibence.webshop.database.UserDB;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muranyibence.webshop.util.Color;
import com.muranyibence.webshop.util.Manufacturer;
import com.muranyibence.webshop.exceptions.ValidationException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import com.muranyibence.webshop.util.Sex;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Bence
 */
public class Main {

    private static List<UserEntity> users;
    private static List<DeviceEntity> devices;
    private static Weld weld;
    private static UserDB userDB;
    private static DeviceDB deviceDB;
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private Main() {
        //empty constructor
    }

    public static void main(String[] args) throws IOException {
        initialize();
        modifyToInvalidUserTest();
        modifyToInvalidDeviceTest();
        modifyToValidDeviceTest();
        weld.shutdown();

    }

    private static void initialize() {
        users = null;
        devices = null;
        weld = new Weld();
        WeldContainer container = weld.initialize();
        userDB = container.instance().select(UserDB.class).get();
        deviceDB = container.instance().select(DeviceDB.class).get();
        try {
            readDeviceDBFromFile();
            readUserDBFromFile();
        } catch (ValidationException | IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void readUserDBFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        users = mapper.readValue(new File("userDB.json"), new TypeReference<List<UserEntity>>() {
        });
        for (UserEntity user : users) {
            userDB.addUser(user);
        }
    }

    private static void readDeviceDBFromFile() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        devices = mapper.readValue(new File("deviceDB.json"), new TypeReference<List<DeviceEntity>>() {
        });
        for (DeviceEntity device : devices) {
            deviceDB.addDevice(device);
        }
    }

    public static void modifyToInvalidUserTest() {
        Date registrationDate = getRelativeDate(-1);
        Date dateOfBirth = getRelativeDate(-2);
        UserEntity userEntity = new UserEntity.Builder()
                .username("username")
                .password("Password1+")
                .firstname("user")
                .lastname("User1")
                .address("1111 Budapest Első utca 1.")
                .phone("+36305555555")
                .email("user1@gmail.com")
                .registrationDate(registrationDate)
                .lastModifiedDate(registrationDate)
                .dateOfBirth(dateOfBirth)
                .sex(Sex.MALE)
                .admin(true).build();
        userDB.addUser(userEntity);
        try {
            userEntity.setPassword(null);
            userDB.modifyUser(userEntity);
        } catch (ValidationException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(),ex);
        }
    }


    public static void modifyToInvalidDeviceTest() {
        String id = UUID.randomUUID().toString();
        DeviceEntity device = new DeviceEntity(id, "7 hosszabb nev", Manufacturer.APPLE, 0, Color.BLACK, 50);
        deviceDB.addDevice(device);
        try {
            device.setColor(Color.BLUE);
            deviceDB.editDevice(device);
        } catch (ValidationException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(),ex);
        }
    }

    public static void modifyToValidDeviceTest() {
        String id = UUID.randomUUID().toString();
        DeviceEntity device = new DeviceEntity(id, "7 S", Manufacturer.APPLE, 0, Color.BLACK, 50);
        deviceDB.addDevice(device);
        device.setColor(Color.WHITE);
        deviceDB.editDevice(device);
    }

    private static Date getRelativeDate(int days) {
        Calendar beforeDays = Calendar.getInstance();
        beforeDays.add(Calendar.DAY_OF_YEAR, days);

        return beforeDays.getTime();
    }

}
