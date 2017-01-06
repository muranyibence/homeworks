/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.webshop;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 *
 * @author Bence
 */
public class UserDB {

    private static final String NOUSER = "There is no user with this name on the database";
    private Map<String, UserEntity> users = new HashMap<>();

    public UserDB() {
        //empty constructor
    }

    public UserEntity addUser(UserEntity user) {
        Date date = new Date();
        user.setRegistrationDate(date);
        user.setLastModifiedDate(date);
        users.put(user.getUsername(), user);
        return user;

    }

    public UserEntity getUser(String username) {
        if (users.get(username) == null) {
            throw new NoSuchElementException(NOUSER);
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

    public UserEntity modifyUser(UserEntity user) {
        if (users.get(user.getUsername()) == null) {
            throw new NoSuchElementException(NOUSER);
        }
        UserEntity userToModify = getUser(user.getUsername());

        userToModify.setLastModifiedDate(new Date());
        users.put(user.getUsername(), user);
        return user;
    }

    public UserEntity deleteUser(UserEntity user) {
        if (users.get(user.getUsername()) == null) {
            throw new NoSuchElementException(NOUSER);
        }

        users.remove(user.getUsername());
        return user;
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
