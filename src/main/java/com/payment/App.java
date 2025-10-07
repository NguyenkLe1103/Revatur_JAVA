package com.payment;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App 
{
    public static void main( String[] args )
    {
        try (ClassPathXmlApplicationContext ctx =
                 new ClassPathXmlApplicationContext("applicationContext.xml")) {

            CheckoutService svc = ctx.getBean("checkoutService", CheckoutService.class);
            svc.checkout("alice@example.com", 49.99);
            CheckoutService svc2 = ctx.getBean("checkoutService", CheckoutService.class);
            svc2.checkout("alice@example.com", 429.99);
            CheckoutService svc3 = ctx.getBean("checkoutService", CheckoutService.class);
            svc3.checkout("alice@example.com", 492.99);
        }
    }
}
