/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.flight;

import com.muranyibence.flight.entity.Flight;
import com.muranyibence.flight.message.MessageBean;
import com.muranyibence.flight.service.FlightCRUDService;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Bence
 */
@Path("flight")
@RequestScoped
public class FlightRESTResource {

    @Context
    private UriInfo context;
    @Inject
    private FlightCRUDService flightCRUDService;
    @Inject
    private MessageBean flightMessageService;

    public FlightRESTResource() {
        //empty
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addFlight(Flight flight) {
        flightCRUDService.createFlight(flight);
        return Response.ok().entity(flight).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeFlight(@PathParam("id") Long id) {
        Flight flight = flightCRUDService.findFlight(id);
        flightCRUDService.removeFlight(id);
        flightMessageService.deletedFlightMessage(flight);
        return Response.ok().entity(flight).build();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFlight(@PathParam("id") Long id) {
        Flight flight = flightCRUDService.findFlight(id);
        return Response.ok().entity(flight).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFlight(Flight flight) {
        flightCRUDService.updateFlight(flight);
        flightMessageService.modifiedFlightMessage(flight);
        return Response.ok().build();
    }

}
