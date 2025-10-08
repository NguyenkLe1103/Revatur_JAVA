package com.ecom.annotation;
import org.springframework.stereotype.Component;

@Component("paymentGateway")
public interface PaymentGateway {
    String charge(String customer, double amount);
}
