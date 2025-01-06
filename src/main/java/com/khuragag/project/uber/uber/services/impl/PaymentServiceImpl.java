package com.khuragag.project.uber.uber.services.impl;

import com.khuragag.project.uber.uber.entities.Payment;
import com.khuragag.project.uber.uber.entities.Ride;
import com.khuragag.project.uber.uber.entities.enums.PaymentStatus;
import com.khuragag.project.uber.uber.exceptions.ResourceNotfoundException;
import com.khuragag.project.uber.uber.repositories.PaymentRepository;
import com.khuragag.project.uber.uber.repositories.RideRepository;
import com.khuragag.project.uber.uber.services.PaymentService;
import com.khuragag.project.uber.uber.strategies.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentStrategyManager paymentStrategyManager;
    private final PaymentRepository paymentRepository;
    private final RideRepository rideRepository;

    @Override
    public Payment createNewPayment(Ride ride) {
        Payment payment =  Payment.builder()
                .ride(ride)
                .amount(ride.getFare())
                .paymentStatus(PaymentStatus.PENDING)
                .paymentMethod(ride.getPaymentMethod())
                .build();
        return paymentRepository.save(payment);
    }

    @Override
    public void processPayment(Ride ride) {
        Payment payment = paymentRepository.findByRide(ride)
                .orElseThrow(()-> new ResourceNotfoundException("payment not found"));
        paymentStrategyManager.getPaymentStrategy(ride.getPaymentMethod())
                .processPayment(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus status) {
      payment.setPaymentStatus(status);
      paymentRepository.save(payment);
    }
}
