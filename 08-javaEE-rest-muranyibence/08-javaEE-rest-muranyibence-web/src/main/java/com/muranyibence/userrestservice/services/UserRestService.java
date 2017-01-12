
package com.muranyibence.userrestservice.services;

import com.muranyibence.userrestservice.entites.UserEntity;
import com.muranyibence.userrestservice.exception.IdNotMatchException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * @author Bence
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRestService {

    private static final Map<String, UserEntity> USERS = new HashMap<>();
 
    /**
    * http://localhost:8080/08-javaEE-rest-muranyibence-web/userrestservice/users 
    */
    @GET
    public List<UserEntity> getAllUsers() {
        return new ArrayList(USERS.values());
    }

    /**
    * http://localhost:8080/08-javaEE-rest-muranyibence-web/userrestservice/users 
    */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public UserEntity addUser(UserEntity user) {
        user.setId(UUID.randomUUID().toString());
        USERS.put(user.getId(), user);
        return user;
    }

    /**
    * http://localhost:8080/08-javaEE-rest-muranyibence-web/userrestservice/users/{id}
    */
    @GET
    @Path("/{id}")
    public UserEntity getUserById(@PathParam("id") String id) {
        return USERS.get(id);
    }

    /**
    * http://localhost:8080/08-javaEE-rest-muranyibence-web/userrestservice/users/{id}
    */
    @PUT
    @Path("/{id}")
    public UserEntity updateUser(@PathParam("id") String id, UserEntity user) {
        if (!id.equals(user.getId())) {
            throw new IdNotMatchException("There is no user with this id");
        }
        USERS.replace(id, user);
        return USERS.get(id);
    }

    /**
    * http://localhost:8080/08-javaEE-rest-muranyibence-web/userrestservice/users/{id}
    */
    @DELETE
    @Path("/{id}")
    public UserEntity deleteUser(@PathParam("id") String id) {
         if (null == USERS.get(id)) {
            throw new IdNotMatchException("There is no user with this id in the database");
        }
       return USERS.remove(id);
    }
}
