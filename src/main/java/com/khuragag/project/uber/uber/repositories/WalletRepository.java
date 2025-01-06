package com.khuragag.project.uber.uber.repositories;

import com.khuragag.project.uber.uber.entities.User;
import com.khuragag.project.uber.uber.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByUser(User user);
}
