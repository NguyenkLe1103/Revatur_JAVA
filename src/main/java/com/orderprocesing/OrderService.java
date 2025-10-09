package com.orderprocesing;

import org.springframework.stereotype.Component;

@Component
public class OrderService {
    private final ShippingService shippingService;
    private final PaymentGateway paymentGateway;

    // Constructor injection (no @Autowired needed on recent Spring)
    public OrderService(ShippingService shippingService, PaymentGateway paymentGateway) {
        this.shippingService = shippingService;
        this.paymentGateway = paymentGateway;
    }

    public void processOrder(Order order) {
        paymentGateway.charge(order);
        shippingService.ship(order);
        System.out.println("Order " + order.getId() + " processed!");
    }
}
