package com.payment;

public interface PaymentGateway {
    String charge(String customer, double amount);
    
}
