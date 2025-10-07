package com.payment;

public class PayPal implements PaymentGateway {
    public String charge(String customer, double amount) {
        // Logic to charge via PayPal
        return "Charged " + amount + " to PayPal account of " + customer;
    }

}
