package com.orderprocesing;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements PaymentGateway {
    @Override
    public void charge(Order order) {
        System.out.println("Charging $" + order.getAmount() + " for order " + order.getId());
    }
}
