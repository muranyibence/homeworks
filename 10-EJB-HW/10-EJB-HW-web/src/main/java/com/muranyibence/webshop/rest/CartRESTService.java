package com.muranyibence.webshop.rest;

import com.muranyibence.webshop.dto.ResultDTO;
import com.muranyibence.webshop.dto.ResultType;
import com.muranyibence.webshop.exeption.NotLoggedInException;
import com.muranyibence.webshop.entities.DeviceEntity;
import com.muranyibence.webshop.entities.ShoppingCart;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Bence
 */
@Path("cart")
@RequestScoped
public class CartRESTService {

    @Context
    private UriInfo context;
    @Inject
    private ShoppingCart cart;
    /**
     * Creates a new instance of CartRESTService
     */
    public CartRESTService() {
        //empty
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO addDeviceToCart(@Context HttpServletRequest request, DeviceEntity device) {
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
    public ResultDTO removeDeviceFromCart(@Context HttpServletRequest request, DeviceEntity device) {
        HttpSession session = request.getSession(true);
        if (session.getAttribute(UserRESTService.USER_KEY) == null) {
            throw new NotLoggedInException("You must log in first to remove device to your cart");
        }
        cart.deleteDeviceFromChart(device.getId(), device.getCount());
        return new ResultDTO(ResultType.SUCCESS, device);
    }

    @Path("buy")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO buyCart(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (session.getAttribute(UserRESTService.USER_KEY) == null) {
            throw new NotLoggedInException("You must log in first to buy your cart");
        }
        List<DeviceEntity> boughtDevices = cart.buyChart();
        return new ResultDTO(ResultType.SUCCESS, boughtDevices);
    }

}
