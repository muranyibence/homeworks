package com.muranyibence.webshop.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Bence
 */
@javax.ws.rs.ApplicationPath("webshop")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.muranyibence.webshop.exception.AuthenticationFailureExceptionMapper.class);
        resources.add(com.muranyibence.webshop.exception.DeviceAlreadyInDBExceptionMapper.class);
        resources.add(com.muranyibence.webshop.exception.DeviceNotExistInDBExceptionMapper.class);
        resources.add(com.muranyibence.webshop.exception.GeneralExceptionMapper.class);
        resources.add(com.muranyibence.webshop.exception.NoPrivilegeExceptionMapper.class);
        resources.add(com.muranyibence.webshop.exception.NotEnoughCountInDBExceptionMapper.class);
        resources.add(com.muranyibence.webshop.exception.NotLoggedInExceptionMapper.class);
        resources.add(com.muranyibence.webshop.exception.UserAlreadyInDBExceptionMapper.class);
        resources.add(com.muranyibence.webshop.exception.UserNotExistInDBExceptionMapper.class);
        resources.add(com.muranyibence.webshop.exception.ValidationExceptionMapper.class);
        resources.add(com.muranyibence.webshop.rest.CartRESTService.class);
        resources.add(com.muranyibence.webshop.rest.DeviceRESTService.class);
        resources.add(com.muranyibence.webshop.rest.UserRESTService.class);
    }

}
