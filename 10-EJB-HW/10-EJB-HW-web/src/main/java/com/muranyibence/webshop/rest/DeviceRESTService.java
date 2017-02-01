package com.muranyibence.webshop.rest;

import com.muranyibence.webshop.dto.ResultDTO;
import com.muranyibence.webshop.dto.ResultType;
import com.muranyibence.webshop.exeption.DeviceNotExistInDBException;
import com.muranyibence.webshop.exeption.NoPrivilegeException;
import com.muranyibence.webshop.exeption.NotLoggedInException;
import com.muranyibence.webshop.exeption.database.DeviceDB;
import com.muranyibence.webshop.entities.DeviceEntity;
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
@Path("device")
@SessionScoped
public class DeviceRESTService implements Serializable {

    public static final String USER_KEY = "user";
    @Inject
    private DeviceDB deviceDB;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO addDevice(@Context HttpServletRequest request, DeviceEntity device) {
        HttpSession session = request.getSession(true);
        if (session.getAttribute(UserRESTService.USER_KEY) == null) {
            throw new NotLoggedInException("You must log in first to add device to the database");
        }
        UserEntity currentUser = (UserEntity) session.getAttribute(UserRESTService.USER_KEY);
        if (!currentUser.isAdmin()) {
            throw new NoPrivilegeException("You need admin rights to add device to the db");
        }
        return new ResultDTO(ResultType.SUCCESS, deviceDB.addDevice(device));
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO deleteDevice(@Context HttpServletRequest request, DeviceEntity device) {
        HttpSession session = request.getSession(true);
        if (session.getAttribute(UserRESTService.USER_KEY) == null) {
            throw new NotLoggedInException("You must log in first to delete device from the database");
        }
        UserEntity currentUser = (UserEntity) session.getAttribute(UserRESTService.USER_KEY);
        if (!currentUser.isAdmin()) {
            throw new NoPrivilegeException("You need admin rights to delete device from the db");
        }

        if (deviceDB.getDevice(device.getId()) == null) {
            throw new DeviceNotExistInDBException("Device " + device.getId() + " not exist in db");
        }

        return new ResultDTO(ResultType.SUCCESS, deviceDB.deleteDevice(device));
    }

    @GET
    @Path("/{deviceid}")
    @Produces(MediaType.APPLICATION_JSON)
    public DeviceEntity getDevice(@PathParam("deviceid") String deviceid) {
        if (deviceDB.getDevice(deviceid) == null) {
            throw new DeviceNotExistInDBException("Device " + deviceid + " not exist in db");
        }
        return deviceDB.getDevice(deviceid);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<DeviceEntity> getAllDevice() {
        return deviceDB.getAllDevice();
    }
}
