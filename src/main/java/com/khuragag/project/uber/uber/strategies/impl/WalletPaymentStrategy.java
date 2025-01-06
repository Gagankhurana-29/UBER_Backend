package com.khuragag.project.uber.uber.strategies.impl;

import com.khuragag.project.uber.uber.entities.Payment;
import com.khuragag.project.uber.uber.entities.Rider;
import com.khuragag.project.uber.uber.entities.Driver;
import com.khuragag.project.uber.uber.entities.enums.PaymentStatus;
import com.khuragag.project.uber.uber.entities.enums.TransactionMethod;
import com.khuragag.project.uber.uber.repositories.PaymentRepository;
import com.khuragag.project.uber.uber.services.PaymentService;
import com.khuragag.project.uber.uber.services.WalletService;
import com.khuragag.project.uber.uber.strategies.PaymentStrategy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Rider rider = payment.getRide().getRider();
        Driver driver = payment.getRide().getDriver();
        double amount = payment.getAmount();
        double driverEarning = amount * (1-Payment_Commission);
        walletService.deductMoneyFromWallet(rider.getUser(), amount,null,
                payment.getRide(), TransactionMethod.RIDE);
        walletService.addMoneyToMyWallet(driver.getUser(),driverEarning,null,
                payment.getRide(), TransactionMethod.RIDE);
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
