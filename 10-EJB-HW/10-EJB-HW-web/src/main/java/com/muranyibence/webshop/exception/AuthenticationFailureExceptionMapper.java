package com.muranyibence.webshop.exception;

import com.muranyibence.webshop.dto.ResultDTO;
import com.muranyibence.webshop.dto.ResultType;
import com.muranyibence.webshop.exeption.AuthenticationFailureException;
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
public class AuthenticationFailureExceptionMapper implements ExceptionMapper<AuthenticationFailureException> {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationFailureExceptionMapper.class.getName());

    @Override
    public Response toResponse(AuthenticationFailureException exception) {
        LOGGER.log(Level.SEVERE, "Authantication failure: ", exception);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ResultDTO(ResultType.ERROR, exception.getMessage() + " - " + exception.getCause())).type(MediaType.APPLICATION_JSON).build();
    }

}
