package com.muranyibence.webshop;

import com.muranyibence.webshop.exeption.UserAlreadyInDBException;
import com.muranyibence.webshop.exeption.UserNotExistInDBException;
import com.muranyibence.webshop.database.UserDB;
import com.muranyibence.webshop.entities.UserEntity;
import com.muranyibence.webshop.util.Sex;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Bence
 */
public class UserDBTest {

    private static UserDB userDB;

    public UserDBTest() {
        // empty
    }

    @BeforeClass
    public static void init() {
        userDB = new UserDB();
    }

    @Test
    public void testAddUser() throws UserAlreadyInDBException, UserNotExistInDBException {

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
                .sex(Sex.MALE)
                .registrationDate(registrationDate)
                .lastModifiedDate(registrationDate)
                .dateOfBirth(dateOfBirth)
                .admin(true).build();
        userDB.addUser(userEntity);

        UserEntity user1 = userDB.getUser("username");
        Assert.assertNotEquals(null, user1);
        userDB.clearDB();
    }

    @Test
    public void testAuthenticate() throws UserAlreadyInDBException {

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
                .sex(Sex.MALE)
                .registrationDate(registrationDate)
                .lastModifiedDate(registrationDate)
                .dateOfBirth(dateOfBirth)
                .admin(true).build();
        userDB.addUser(userEntity);
        boolean isValidUser = userDB.authenticate("username", "Password1+");
        Assert.assertEquals(true, isValidUser);
        userDB.clearDB();
    }

    @Test
    public void testModifyUser() throws UserAlreadyInDBException, UserNotExistInDBException {

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
                .sex(Sex.MALE)
                .registrationDate(registrationDate)
                .lastModifiedDate(registrationDate)
                .dateOfBirth(dateOfBirth)
                .admin(true)
                .build();
        userDB.addUser(userEntity);
        userEntity.setLastname("User2");
        UserEntity user2 = userDB.modifyUser(userEntity);
        Assert.assertEquals(user2.getLastname(), "User2");
        userDB.clearDB();
    }

    @Test(expected = UserNotExistInDBException.class)
    public void testRemoveUser() throws UserAlreadyInDBException, UserNotExistInDBException {

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
                .sex(Sex.MALE)
                .registrationDate(registrationDate)
                .lastModifiedDate(registrationDate)
                .dateOfBirth(dateOfBirth)
                .admin(true)
                .build();
        userDB.addUser(userEntity);
        userDB.deleteUser(userEntity);
        Assert.assertEquals(1, userDB.getUser(userEntity.getUsername()));
        userDB.clearDB();
    }

    @Test
    public void testGetAllUser() throws UserAlreadyInDBException {

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
                .sex(Sex.MALE)
                .registrationDate(registrationDate)
                .lastModifiedDate(registrationDate)
                .dateOfBirth(dateOfBirth)
                .admin(true)
                .build();
        userDB.addUser(userEntity);
        userEntity.setUsername("username2");
        userDB.addUser(userEntity);
        List<UserEntity> allUser = userDB.getAllUser();
        Assert.assertEquals(2, allUser.size());
        userDB.clearDB();

    }

    private Date getRelativeDate(int days) {
        Calendar beforeDays = Calendar.getInstance();
        beforeDays.add(Calendar.DAY_OF_YEAR, days);

        return beforeDays.getTime();
    }
}
