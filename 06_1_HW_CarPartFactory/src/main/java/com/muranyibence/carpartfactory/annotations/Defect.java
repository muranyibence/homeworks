/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.carpartfactory.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Bence
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE}) //can use in method only.
@Documented
public @interface Defect {

    String author() default "Anonymus";
    String message() default "There is a problem with this product";
}
