package com.ecom.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        
        CheckoutService svc = ctx.getBean(CheckoutService.class);
        svc.checkout("ctor@example.com", 300.00);
    }
}
