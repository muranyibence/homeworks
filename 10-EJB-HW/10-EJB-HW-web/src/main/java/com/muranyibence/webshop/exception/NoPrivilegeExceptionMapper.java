package com.muranyibence.webshop.exception;

import com.muranyibence.webshop.dto.ResultDTO;
import com.muranyibence.webshop.dto.ResultType;
import com.muranyibence.webshop.exeption.NoPrivilegeException;
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
public class NoPrivilegeExceptionMapper implements ExceptionMapper<NoPrivilegeException> {

    private static final Logger LOGGER = Logger.getLogger(NoPrivilegeExceptionMapper.class.getName());

    @Override
    public Response toResponse(NoPrivilegeException exception) {
        LOGGER.log(Level.SEVERE, "NoPrivilegeException: ", exception);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ResultDTO(ResultType.ERROR, exception.getMessage() + " - " + exception.getCause())).type(MediaType.APPLICATION_JSON).build();
    }

}
