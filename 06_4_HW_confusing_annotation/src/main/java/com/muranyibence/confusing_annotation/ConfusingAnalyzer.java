/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.confusing_annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bence
 */
public class ConfusingAnalyzer {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    public ConfusingAnalyzer() {
    }

    public static void analyze(final Class<?> clazz) {

        Confusing confusingClass = clazz.getAnnotation(Confusing.class);
        if (confusingClass != null) {
            LOGGER.log(Level.INFO,
                    "\n Author: {0}\n Class name: {1}\n Package: {2}\n Confusing message: {3} \n Methods: \n",
                    new Object[]{confusingClass.author(), clazz.getName(), clazz.getPackage(), clazz.getAnnotation(Confusing.class).message()});
        }

        for (final Method method : clazz.getDeclaredMethods()) {
            Confusing confusingMethod = method.getAnnotation(Confusing.class);
            if (confusingMethod != null) {
                LOGGER.log(Level.INFO, "\n Author: {0}\n Name: {1} \n number of parameters: {2} \n Return type: {3} \n Confusing message: {4}",
                        new Object[]{confusingMethod.author(), method.getName(), method.getParameterCount(), method.getReturnType(), method.getAnnotation(Confusing.class).message()});
            }
        }
    }

}
