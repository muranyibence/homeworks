package com.muranyibence.webshop.exception;

import com.muranyibence.webshop.dto.ResultDTO;
import com.muranyibence.webshop.dto.ResultType;
import com.muranyibence.webshop.exeption.DeviceNotExistInDBException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Bence
 */
@Provider
public class DeviceNotExistInDBExceptionMapper implements ExceptionMapper<DeviceNotExistInDBException> {

    private static final Logger LOGGER = Logger.getLogger(DeviceNotExistInDBExceptionMapper.class.getName());

    @Override
    public Response toResponse(DeviceNotExistInDBException exception) {
        LOGGER.log(Level.SEVERE, "DeviceNotExistInDBException Exception: ", exception);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ResultDTO(ResultType.ERROR, exception.getMessage() + " - " + exception.getCause())).type(MediaType.APPLICATION_JSON).build();
    }

}
