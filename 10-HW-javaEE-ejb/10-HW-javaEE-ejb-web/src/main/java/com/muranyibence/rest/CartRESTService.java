package com.muranyibence.rest;

import com.muranyibence.dto.ResultDTO;
import com.muranyibence.dto.ResultType;
import com.muranyibence.webshop.entites.DeviceEntity;
import com.muranyibence.webshop.entites.ShoppingCart;
import com.muranyibence.webshop.entites.UserEntity;
import com.muranyibence.webshop.exceptions.DeviceAlreadyInDBException;
import com.muranyibence.webshop.exceptions.DeviceNotExistInDBException;
import com.muranyibence.webshop.exceptions.NoPrivilegeException;
import com.muranyibence.webshop.exceptions.NotEnoughCountInDBException;
import com.muranyibence.webshop.exceptions.NotLoggedInException;
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
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Bence
 */
@Path("cart")
@SessionScoped
public class CartRESTService implements Serializable {

    @Inject
    private ShoppingCart cart;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO addDeviceToCart(@Context HttpServletRequest request, DeviceEntity device) throws NotLoggedInException, NotEnoughCountInDBException, DeviceNotExistInDBException {
        HttpSession session = request.getSession(true);
        if (session.getAttribute(UserRESTService.USER_KEY) == null) {
            throw new NotLoggedInException("You must log in first to add device to your cart");
        }
        cart.addDeviceToChart(device.getId(), device.getCount());
        return new ResultDTO(ResultType.SUCCESS, device);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO removeDeviceFromCart(@Context HttpServletRequest request, DeviceEntity device) throws NotLoggedInException, NotEnoughCountInDBException, DeviceNotExistInDBException {
        HttpSession session = request.getSession(true);
        if (session.getAttribute(UserRESTService.USER_KEY) == null) {
            throw new NotLoggedInException("You must log in first to add device to your cart");
        }
        cart.deleteDeviceFromChart(device.getId(), device.getCount());
        return new ResultDTO(ResultType.SUCCESS, device);
    }

    @Path("buy")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO BuyCart(@Context HttpServletRequest request) throws NotLoggedInException {
        HttpSession session = request.getSession(true);
        if (session.getAttribute(UserRESTService.USER_KEY) == null) {
            throw new NotLoggedInException("You must log in first to add device to your cart");
        }
        List<DeviceEntity> boughtDevices = cart.buyChart();
        return new ResultDTO(ResultType.SUCCESS, boughtDevices);
    }

}
