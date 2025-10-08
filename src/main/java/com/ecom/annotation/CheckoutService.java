package com.ecom.annotation;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


@Component("checkoutService")
public class CheckoutService {
    private PaymentGateway gateway;   // injected by Spring

    @Autowired
    @Qualifier("paypalGateway") // or "creditCardGateway"
    public void setGateway(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    public void checkout(String email, double amount) {
        if (gateway == null) throw new IllegalStateException("PaymentGateway not configured");
        String result = gateway.charge(email, amount);
        System.out.println(result);
    }
}