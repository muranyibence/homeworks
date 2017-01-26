package com.muranyibence.rest;

import com.muranyibence.dto.ResultDTO;
import com.muranyibence.dto.ResultType;
import com.muranyibence.webshop.database.UserDB;
import com.muranyibence.webshop.entites.UserEntity;
import com.muranyibence.webshop.exceptions.AuthenticationFailureException;
import com.muranyibence.webshop.exceptions.NoPrivilegeException;
import com.muranyibence.webshop.exceptions.NotLoggedInException;
import com.muranyibence.webshop.exceptions.UserAlreadyInDBException;
import com.muranyibence.webshop.exceptions.UserNotExistInDBException;
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
    private UserDB users;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO login(@Context HttpServletRequest request, UserEntity user) throws AuthenticationFailureException {
        UserEntity userEntity = users.authenticateUser(user.getUsername(), user.getPassword());
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(2000);
        session.setAttribute(USER_KEY, userEntity);
        return new ResultDTO(ResultType.SUCCESS, userEntity);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO addUser(@Context HttpServletRequest request, UserEntity user) throws NotLoggedInException, NoPrivilegeException, UserAlreadyInDBException {
        HttpSession session = request.getSession(true);
        if (session.getAttribute(UserRESTService.USER_KEY) == null) {
            throw new NotLoggedInException("You must log in first to add users to the database");
        }
        UserEntity currentUser = (UserEntity) session.getAttribute(UserRESTService.USER_KEY);
        if (!currentUser.isAdmin()) {
            throw new NoPrivilegeException("You need admin rights to add user to the db");
        }
        return new ResultDTO(ResultType.SUCCESS, users.addUser(user));
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO deleteUser(@Context HttpServletRequest request, UserEntity user) throws NotLoggedInException, NoPrivilegeException, UserNotExistInDBException {
        HttpSession session = request.getSession(true);
        if (session.getAttribute(UserRESTService.USER_KEY) == null) {
            throw new NotLoggedInException("You must log in first to delete users from the database");
        }
        UserEntity currentUser = (UserEntity) session.getAttribute(UserRESTService.USER_KEY);
        if (!currentUser.isAdmin()) {
            throw new NoPrivilegeException("You need admin rights to delete user from the db");
        }
        UserEntity deletedUser = users.deleteUser(user);
        return new ResultDTO(ResultType.SUCCESS, deletedUser);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserEntity> getAllUser() {
        return users.getAllUser();
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserEntity getUser(@PathParam("username") String username) throws UserNotExistInDBException {
        if (users.getUser(username) == null) {
            throw new UserNotExistInDBException("User " + username + " not exist in db");
        }
        return users.getUser(username);
    }

    @POST
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO logout(@Context HttpServletRequest request) throws AuthenticationFailureException {
        request.getSession().invalidate();
        return new ResultDTO(ResultType.SUCCESS, "Logged out");
    }

}
