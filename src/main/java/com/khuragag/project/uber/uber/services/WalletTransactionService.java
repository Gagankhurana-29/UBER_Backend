package com.khuragag.project.uber.uber.services;

import com.khuragag.project.uber.uber.entities.DTO.WalletTransactionDTO;
import com.khuragag.project.uber.uber.entities.WalletTransaction;

public interface WalletTransactionService {

    void createNewWalletTransaction(WalletTransaction walletTransaction);

}
