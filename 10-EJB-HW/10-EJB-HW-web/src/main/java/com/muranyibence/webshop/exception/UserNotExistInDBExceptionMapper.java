package com.muranyibence.webshop.exception;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import com.muranyibence.webshop.dto.ResultDTO;
import com.muranyibence.webshop.dto.ResultType;
import com.muranyibence.webshop.exeption.UserNotExistInDBException;

/**
 *
 * @author Bence
 */
@Provider
public class UserNotExistInDBExceptionMapper implements ExceptionMapper<UserNotExistInDBException> {

    private static final Logger LOGGER = Logger.getLogger(UserNotExistInDBExceptionMapper.class.getName());

    @Override
    public Response toResponse(UserNotExistInDBException exception) {
        LOGGER.log(Level.SEVERE, "UserNotExistInDBException Exception: ", exception);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ResultDTO(ResultType.ERROR, exception.getMessage() + " - " + exception.getCause())).type(MediaType.APPLICATION_JSON).build();
    }

}
