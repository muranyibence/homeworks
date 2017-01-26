package com.muranyibence.exception;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import com.muranyibence.dto.ResultDTO;
import com.muranyibence.dto.ResultType;
import javax.inject.Inject;

/**
 *
 * @author Bence
 */
@Provider
public class GeneralExceptionMapper implements ExceptionMapper<Exception> {

    @Inject  
    private transient Logger logger;  
    @Override
    public Response toResponse(Exception throwable) {
        logger.log(Level.SEVERE, "General Exception", throwable);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ResultDTO(ResultType.ERROR, throwable.getMessage() + " - " + throwable.getCause())).type(MediaType.APPLICATION_JSON).build();
    }

}
