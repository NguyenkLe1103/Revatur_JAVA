package com.orderprocesing;

public interface PaymentGateway {

    void charge(Order order);
} 
