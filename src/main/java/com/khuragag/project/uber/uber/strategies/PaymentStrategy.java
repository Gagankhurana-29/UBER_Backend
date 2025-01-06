package com.khuragag.project.uber.uber.strategies;

import com.khuragag.project.uber.uber.entities.Payment;

public interface PaymentStrategy {

    Double Payment_Commission = 0.3;
    void processPayment(Payment payment);
}
