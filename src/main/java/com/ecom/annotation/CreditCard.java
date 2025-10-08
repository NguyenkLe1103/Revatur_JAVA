package com.ecom.annotation;


import org.springframework.stereotype.Component;
@Component("creditCardGateway")
public class CreditCard implements PaymentGateway {
    public String charge(String customer, double amount) {
        // Logic to charge the credit card
        return "Charged " + amount + " to credit card of " + customer;
    }

}
