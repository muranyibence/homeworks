package com.muranyibence.async;

import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;

/**
 *
 * @author Bence
 */
public class AsyncDemonstrator {

    private static final Logger LOGGER = Logger.getLogger(AsyncDemonstrator.class.getName());
    private Long startTime;
    private Long endTime;
    private Long elapsedTime;
    private Long startFutureTime;
    private Long endFutureTime;
    private Long elapsedFutureTime;

    @Asynchronous
    public void workSomething() {
        startTime = System.currentTimeMillis();
        LOGGER.log(Level.INFO, "Start workSomething");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AsyncDemonstrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        LOGGER.log(Level.INFO, "workSomething done, elapsed time: " + elapsedTime);
    }

    @Asynchronous
    public Future<String> workSomethingWithResult() {
        startFutureTime = System.currentTimeMillis();
        LOGGER.log(Level.INFO, "Start working on work with result");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AsyncDemonstrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        endFutureTime = System.currentTimeMillis();
        elapsedFutureTime = endFutureTime - startFutureTime;
        LOGGER.log(Level.INFO, "workSomethingWithResult done, elapsed time: " + elapsedFutureTime);
        return new AsyncResult<>("workSomethingWithResult done, elapsed time: " + elapsedFutureTime);
    }

}
