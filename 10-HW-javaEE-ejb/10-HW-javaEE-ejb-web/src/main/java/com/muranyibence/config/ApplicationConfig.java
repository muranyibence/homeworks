package com.muranyibence.config;

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
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.muranyibence.exception.AuthenticationFailureExceptionMapper.class);
        resources.add(com.muranyibence.exception.DeviceAlreadyInDBExceptionMapper.class);
        resources.add(com.muranyibence.exception.DeviceNotExistInDBExceptionMapper.class);
        resources.add(com.muranyibence.exception.GeneralExceptionMapper.class);
        resources.add(com.muranyibence.exception.NoPrivilegeExceptionMapper.class);
        resources.add(com.muranyibence.exception.NotEnoughCountInDBExceptionMapper.class);
        resources.add(com.muranyibence.exception.NotLoggedInExceptionMapper.class);
        resources.add(com.muranyibence.exception.UserAlreadyInDBExceptionMapper.class);
        resources.add(com.muranyibence.exception.UserNotExistInDBExceptionMapper.class);
        resources.add(com.muranyibence.exception.ValidationExceptionMapper.class);
        resources.add(com.muranyibence.rest.CartRESTService.class);
        resources.add(com.muranyibence.rest.DeviceRESTService.class);
        resources.add(com.muranyibence.rest.UserRESTService.class);
    }

}
