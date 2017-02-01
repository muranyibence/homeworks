package com.muranyibence.webshop.exception;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import com.muranyibence.webshop.dto.ResultDTO;
import com.muranyibence.webshop.dto.ResultType;
import com.muranyibence.webshop.exeption.UserAlreadyInDBException;

/**
 *
 * @author Bence
 */
@Provider
public class UserAlreadyInDBExceptionMapper implements ExceptionMapper<UserAlreadyInDBException> {

    private static final Logger LOGGER = Logger.getLogger(UserAlreadyInDBExceptionMapper.class.getName());

    @Override
    public Response toResponse(UserAlreadyInDBException exception) {
        LOGGER.log(Level.SEVERE, "UserAlreadyInDBException Exception: ", exception);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ResultDTO(ResultType.ERROR, exception.getMessage() + " - " + exception.getCause())).type(MediaType.APPLICATION_JSON).build();
    }

}
