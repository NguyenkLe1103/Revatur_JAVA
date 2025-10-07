package com.payment;

public class CheckoutService {
    private PaymentGateway gateway;   // injected by Spring

    // setter injection
    public void setGateway(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    public void checkout(String email, double amount) {
        if (gateway == null) throw new IllegalStateException("PaymentGateway not configured");
        String result = gateway.charge(email, amount);
        System.out.println(result);
    }
}

