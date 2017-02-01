package com.muranyibence.webshop.rest;

import com.muranyibence.webshop.dto.ResultDTO;
import com.muranyibence.webshop.dto.ResultType;
import com.muranyibence.webshop.exeption.NoPrivilegeException;
import com.muranyibence.webshop.exeption.NotLoggedInException;
import com.muranyibence.webshop.exeption.UserNotExistInDBException;
import com.muranyibence.webshop.database.UserDB;
import com.muranyibence.webshop.entities.UserEntity;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Bence
 */
@Path("user")
@SessionScoped
public class UserRESTService implements Serializable {

    public static final String USER_KEY = "user";

    @Inject
    private UserDB userDB;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO login(@Context HttpServletRequest request, UserEntity user) {
        UserEntity userEntity = userDB.authenticateUser(user.getUsername(), user.getPassword());
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(2000);
        session.setAttribute(USER_KEY, userEntity);
        return new ResultDTO(ResultType.SUCCESS, userEntity);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO addUser(@Context HttpServletRequest request, UserEntity user) {
        HttpSession session = request.getSession(true);
        if (session.getAttribute(UserRESTService.USER_KEY) == null) {
            throw new NotLoggedInException("You must log in first to add users to the database");
        }
        UserEntity currentUser = (UserEntity) session.getAttribute(UserRESTService.USER_KEY);
        if (!currentUser.isAdmin()) {
            throw new NoPrivilegeException("You need admin rights to add user to the db");
        }
        return new ResultDTO(ResultType.SUCCESS, userDB.addUser(user));
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO deleteUser(@Context HttpServletRequest request, UserEntity user) {
        HttpSession session = request.getSession(true);
        if (session.getAttribute(UserRESTService.USER_KEY) == null) {
            throw new NotLoggedInException("You must log in first to delete users from the database");
        }
        UserEntity currentUser = (UserEntity) session.getAttribute(UserRESTService.USER_KEY);
        if (!currentUser.isAdmin()) {
            throw new NoPrivilegeException("You need admin rights to delete user from the db");
        }
        UserEntity deletedUser = userDB.deleteUser(user);
        return new ResultDTO(ResultType.SUCCESS, deletedUser);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserEntity> getAllUser() {
        return userDB.getAllUser();
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserEntity getUser(@PathParam("username") String username) {
        if (userDB.getUser(username) == null) {
            throw new UserNotExistInDBException("User " + username + " not exist in db");
        }
        return userDB.getUser(username);
    }

    @POST
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO logout(@Context HttpServletRequest request) {
        request.getSession().invalidate();
        return new ResultDTO(ResultType.SUCCESS, "Logged out");
    }

}
