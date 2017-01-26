package com.muranyibence.webshop.producer;

import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author Bence
 */
public class LoggerProducer {  
   /** 
    * @param injectionPoint 
    * @return logger 
    */  
    @Produces  
    public Logger produceLogger(InjectionPoint injectionPoint) {  
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());  
    }  
}  
