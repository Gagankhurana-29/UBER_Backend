package com.khuragag.project.uber.uber.strategies;

import com.khuragag.project.uber.uber.entities.enums.PaymentMethod;
import com.khuragag.project.uber.uber.strategies.impl.CODPaymentStrategy;
import com.khuragag.project.uber.uber.strategies.impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CODPaymentStrategy codPaymentStrategy;

    public PaymentStrategy getPaymentStrategy(PaymentMethod paymentMethod) {
        return switch(paymentMethod) {
            case WALLET -> walletPaymentStrategy;
            case CASH -> codPaymentStrategy;
        };
    }

}
