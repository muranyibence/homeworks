package com.muranyibence.webshop;

import com.muranyibence.webshop.util.Sex;
import com.muranyibence.webshop.entites.UserEntity;
import com.muranyibence.webshop.exceptions.UserNotExistInDBException;
import com.muranyibence.webshop.database.UserDB;
import com.muranyibence.webshop.exceptions.UserAlreadyInDBException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Bence
 */
public class UserDBTest {

    private UserDB userDB;

    public UserDBTest() {
        // empty
    }

    @Test
    public void testAddUser() throws UserAlreadyInDBException, UserNotExistInDBException {
        userDB = UserDB.getInstance();

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
        userDB.cleanDB();
    }

    @Test
    public void testAuthenticate() throws UserAlreadyInDBException {
        userDB = UserDB.getInstance();

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
        userDB.cleanDB();
    }

    @Test
    public void testModifyUser() throws UserAlreadyInDBException, UserNotExistInDBException {
        userDB = UserDB.getInstance();

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
        userDB.cleanDB();
    }

    @Test(expected = UserNotExistInDBException.class)
    public void testRemoveUser() throws UserAlreadyInDBException, UserNotExistInDBException {
        userDB = UserDB.getInstance();

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
        userDB.cleanDB();
    }

    @Test
    public void testGetAllUser() throws UserAlreadyInDBException {
        userDB = UserDB.getInstance();

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
        userDB.cleanDB();

    }

    private Date getRelativeDate(int days) {
        Calendar beforeDays = Calendar.getInstance();
        beforeDays.add(Calendar.DAY_OF_YEAR, days);

        return beforeDays.getTime();
    }
}
