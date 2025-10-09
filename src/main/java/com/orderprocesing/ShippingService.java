package com.orderprocesing;

import org.springframework.stereotype.Component;

@Component
public class ShippingService {
    public void ship(Order order) {
        System.out.println("Shipping order " + order.getId());
    }
}
