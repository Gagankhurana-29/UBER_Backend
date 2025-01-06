package com.khuragag.project.uber.uber.services.impl;

import com.khuragag.project.uber.uber.entities.Ride;
import com.khuragag.project.uber.uber.entities.User;
import com.khuragag.project.uber.uber.entities.Wallet;
import com.khuragag.project.uber.uber.entities.WalletTransaction;
import com.khuragag.project.uber.uber.entities.enums.TransactionMethod;
import com.khuragag.project.uber.uber.entities.enums.TransactionType;
import com.khuragag.project.uber.uber.exceptions.ResourceNotfoundException;
import com.khuragag.project.uber.uber.repositories.WalletRepository;
import com.khuragag.project.uber.uber.services.WalletService;
import com.khuragag.project.uber.uber.services.WalletTransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletTransactionService walletTransactionService;


    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet addMoneyToMyWallet(User user, Double amount, String TransactionID,
                                     Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance() + amount);

        WalletTransaction walletTransaction = WalletTransaction
                .builder().transactionId(TransactionID)
                .amount(amount)
                .transactionMethod(transactionMethod)
                .transactionType(TransactionType.CREDIT)
                .wallet(wallet)
                .ride(ride)
                .build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);
//        wallet.getTransactions().add(walletTransaction);
        Wallet savedWallet = walletRepository.save(wallet);
        return savedWallet;
    }

    @Override
    public Wallet deductMoneyFromWallet(User user, Double amount, String TransactionID,
                                        Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance() - amount);
        WalletTransaction walletTransaction = WalletTransaction
                .builder().transactionId(TransactionID)
                .amount(amount)
                .transactionMethod(transactionMethod)
                .transactionType(TransactionType.DEBIT)
                .wallet(wallet)
                .ride(ride)
                .build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);
        Wallet savedWallet = walletRepository.save(wallet);
        return savedWallet;
    }

    @Override
    public void withdrawAllMyMoneyFromMyWallet() {

    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new ResourceNotfoundException("Wallet not found with id " + walletId));
    }

    @Override
    public Wallet findByUser(User user) {
        return walletRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotfoundException("User not found with id " + user));
    }
}
