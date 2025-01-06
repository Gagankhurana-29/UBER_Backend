package com.khuragag.project.uber.uber.strategies.impl;

import com.khuragag.project.uber.uber.entities.Driver;
import com.khuragag.project.uber.uber.entities.Payment;
import com.khuragag.project.uber.uber.entities.Wallet;
import com.khuragag.project.uber.uber.entities.enums.PaymentStatus;
import com.khuragag.project.uber.uber.entities.enums.TransactionMethod;
import com.khuragag.project.uber.uber.repositories.PaymentRepository;
import com.khuragag.project.uber.uber.services.PaymentService;
import com.khuragag.project.uber.uber.services.WalletService;
import com.khuragag.project.uber.uber.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CODPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        double platform_comission = payment.getAmount()*Payment_Commission;
        walletService.deductMoneyFromWallet(
                driver.getUser(),
                platform_comission,
                null,
                payment.getRide(),
                TransactionMethod.RIDE);
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
