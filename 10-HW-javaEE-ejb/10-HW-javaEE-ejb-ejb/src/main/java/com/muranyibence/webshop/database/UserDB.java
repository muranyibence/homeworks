package com.muranyibence.webshop.database;

import com.muranyibence.webshop.exceptions.UserAlreadyInDBException;
import com.muranyibence.webshop.entites.UserEntity;
import com.muranyibence.webshop.exceptions.UserNotExistInDBException;
import com.muranyibence.webshop.exceptions.AuthenticationFailureException;
import com.muranyibence.webshop.interceptors.ValidatorInterceptor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.ejb.Singleton;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptors;

/**
 *
 * @author Bence
 */
@Interceptors(ValidatorInterceptor.class)
@Singleton
public class UserDB implements Serializable {

    private static UserDB instance = null;
    private static final String NOUSER = "There is no user with this name on the database: ";
    private Map<String, UserEntity> users = new HashMap<>();

    protected UserDB() {
        // Exists only to defeat instantiation.
    }

    public static UserDB getInstance() {
        if (instance == null) {
            instance = new UserDB();
        }
        return instance;
    }

    public static void cleanDB() {
        instance = null;
    }

    @ExcludeClassInterceptors
    public UserEntity addUser(UserEntity user) throws UserAlreadyInDBException {
        if (users.containsKey(user.getUsername())) {
            throw new UserAlreadyInDBException(new String("User " + user.getUsername() + " is already in database"));
        }
        Date date = new Date();
        user.setRegistrationDate(date);
        user.setLastModifiedDate(date);
        users.put(user.getUsername(), user);
        return user;

    }

    public UserEntity getUser(String username) throws UserNotExistInDBException {
        if (users.get(username) == null) {
            throw new UserNotExistInDBException(NOUSER + username);
        }
        return users.get(username);

    }

    public UserEntity authenticateUser(String username, String password) throws AuthenticationFailureException {
        if (!users.containsKey(username) || !password.equals(users.get(username).getPassword())) {
            throw new AuthenticationFailureException();
        }
        return users.get(username);
    }

    public boolean authenticate(String username, String password) {
        UserEntity user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;

    }

    public UserEntity modifyUser(UserEntity user) throws UserNotExistInDBException {
        if (users.get(user.getUsername()) == null) {
            throw new UserNotExistInDBException(NOUSER + user.getUsername());
        }
        UserEntity userToModify = getUser(user.getUsername());

        userToModify.setLastModifiedDate(new Date());
        users.put(user.getUsername(), user);
        return user;
    }

    public UserEntity deleteUser(UserEntity user) throws UserNotExistInDBException {
        if (users.get(user.getUsername()) == null) {
            throw new UserNotExistInDBException(NOUSER + user.getUsername());
        }
        return users.remove(user.getUsername());
    }

    public List<UserEntity> getAllUser() {
        List<UserEntity> userList = new ArrayList();
        userList.addAll(users.values());
        return userList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.users);
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
        final UserDB other = (UserDB) obj;
        if (!Objects.equals(this.users, other.users)) {
            return false;
        }
        return true;
    }

}
