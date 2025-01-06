package com.khuragag.project.uber.uber.services;

import com.khuragag.project.uber.uber.entities.Payment;
import com.khuragag.project.uber.uber.entities.Ride;
import com.khuragag.project.uber.uber.entities.enums.PaymentStatus;

public interface PaymentService {

    Payment createNewPayment(Ride ride);

    void processPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus status);

}
