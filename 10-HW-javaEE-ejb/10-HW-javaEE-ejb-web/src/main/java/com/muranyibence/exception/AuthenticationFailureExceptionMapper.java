package com.muranyibence.exception;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import com.muranyibence.dto.ResultDTO;
import com.muranyibence.dto.ResultType;
import com.muranyibence.webshop.exceptions.AuthenticationFailureException;
import javax.inject.Inject;

/**
 *
 * @author Bence
 */
@Provider
public class AuthenticationFailureExceptionMapper implements ExceptionMapper<AuthenticationFailureException> {

    @Inject  
    private transient Logger logger;  
    @Override
    public Response toResponse(AuthenticationFailureException exception) {
        logger.log(Level.SEVERE, "Authantication failure: ", exception);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ResultDTO(ResultType.ERROR, exception.getMessage() + " - " + exception.getCause())).type(MediaType.APPLICATION_JSON).build();
    }


}
