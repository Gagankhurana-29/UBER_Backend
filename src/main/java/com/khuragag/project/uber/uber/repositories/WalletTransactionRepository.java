package com.khuragag.project.uber.uber.repositories;

import com.khuragag.project.uber.uber.entities.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Long> {
}
