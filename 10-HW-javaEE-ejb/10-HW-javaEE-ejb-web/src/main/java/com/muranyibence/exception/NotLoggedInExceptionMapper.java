package com.muranyibence.exception;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import com.muranyibence.dto.ResultDTO;
import com.muranyibence.dto.ResultType;
import com.muranyibence.webshop.exceptions.NotLoggedInException;
import javax.inject.Inject;
/**
 *
 * @author Bence
 */
@Provider
public class NotLoggedInExceptionMapper implements ExceptionMapper<NotLoggedInException> {

    @Inject  
    private transient Logger logger;  
    @Override
    public Response toResponse(NotLoggedInException exception) {
        logger.log(Level.SEVERE, "NotLoggedInException: ", exception);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ResultDTO(ResultType.ERROR, exception.getMessage() + " - " + exception.getCause())).type(MediaType.APPLICATION_JSON).build();
    }


}
