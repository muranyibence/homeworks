package com.muranyibence.webshop.producer;

import com.muranyibence.webshop.entites.ShoppingCart;
import javax.enterprise.inject.Produces;

/**
 *
 * @author Bence
 */
public class CartProducer {

    @Produces
    public ShoppingCart produce() {

        return new ShoppingCart();

    }

}
