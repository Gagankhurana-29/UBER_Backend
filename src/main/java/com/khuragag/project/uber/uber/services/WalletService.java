package com.khuragag.project.uber.uber.services;

import com.khuragag.project.uber.uber.entities.Ride;
import com.khuragag.project.uber.uber.entities.User;
import com.khuragag.project.uber.uber.entities.Wallet;
import com.khuragag.project.uber.uber.entities.enums.TransactionMethod;

import java.util.Optional;

public interface WalletService {

    Wallet createNewWallet(User user);

    Wallet addMoneyToMyWallet(User user, Double amount, String TransactionID,
                              Ride ride, TransactionMethod transactionMethod);

    Wallet deductMoneyFromWallet(User user, Double amount, String TransactionID,
                                 Ride ride, TransactionMethod transactionMethod);

    void withdrawAllMyMoneyFromMyWallet();

    Wallet findWalletById(Long walletId);

    Wallet findByUser(User user);
}
