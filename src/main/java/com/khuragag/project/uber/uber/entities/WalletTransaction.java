package com.khuragag.project.uber.uber.entities;

import com.khuragag.project.uber.uber.entities.enums.TransactionMethod;
import com.khuragag.project.uber.uber.entities.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(indexes = {
        @Index(name = "idx_walletTransaction_wallet", columnList = "wallet_id")
})
public class WalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    @CreationTimestamp
    private LocalDateTime transactionTime;

    @ManyToOne
    private Ride ride;

    @ManyToOne(fetch = FetchType.LAZY)
    private Wallet wallet;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private TransactionMethod transactionMethod;

    private String transactionId;
}
