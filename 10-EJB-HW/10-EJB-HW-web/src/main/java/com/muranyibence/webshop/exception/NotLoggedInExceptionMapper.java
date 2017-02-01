package com.muranyibence.webshop.exception;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import com.muranyibence.webshop.dto.ResultDTO;
import com.muranyibence.webshop.dto.ResultType;
import com.muranyibence.webshop.exeption.NotLoggedInException;

/**
 *
 * @author Bence
 */
@Provider
public class NotLoggedInExceptionMapper implements ExceptionMapper<NotLoggedInException> {

    private static final Logger LOGGER = Logger.getLogger(NotLoggedInExceptionMapper.class.getName());

    @Override
    public Response toResponse(NotLoggedInException exception) {
        LOGGER.log(Level.SEVERE, "NotLoggedInException: ", exception);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ResultDTO(ResultType.ERROR, exception.getMessage() + " - " + exception.getCause())).type(MediaType.APPLICATION_JSON).build();
    }

}
