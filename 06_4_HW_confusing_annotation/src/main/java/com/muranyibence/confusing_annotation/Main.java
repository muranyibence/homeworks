/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.confusing_annotation;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bence
 */
@Confusing(author="Bence")
public class Main {
    

    public static void main(String[] args) {
        ConfusingAnalyzer.analyze(Main.class);
    }
    
    
    @Confusing(author="Bence", message="I don't understand this")
    public static int confusingMethod(int a, int b) {
        return a+b;
    }

  
    public static int anotherConfusingMethod(int a, int b) {
        return a-b;
    }
}