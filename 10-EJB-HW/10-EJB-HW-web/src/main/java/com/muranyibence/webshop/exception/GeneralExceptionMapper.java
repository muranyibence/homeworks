package com.muranyibence.webshop.exception;

import com.muranyibence.webshop.dto.ResultDTO;
import com.muranyibence.webshop.dto.ResultType;
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
public class GeneralExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOGGER = Logger.getLogger(GeneralExceptionMapper.class.getName());

    @Override
    public Response toResponse(Exception throwable) {
        LOGGER.log(Level.SEVERE, "General Exception", throwable);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ResultDTO(ResultType.ERROR, throwable.getMessage() + " - " + throwable.getCause())).type(MediaType.APPLICATION_JSON).build();
    }

}
