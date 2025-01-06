package com.khuragag.project.uber.uber.entities.DTO;

import com.khuragag.project.uber.uber.entities.Ride;
import com.khuragag.project.uber.uber.entities.Wallet;
import com.khuragag.project.uber.uber.entities.enums.TransactionMethod;
import com.khuragag.project.uber.uber.entities.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletTransactionDTO {
        private Long id;

        private double amount;

        private LocalDateTime transactionTime;

        private Wallet wallet;

        private RideDTO ride;

        private TransactionType transactionType;

        private TransactionMethod transactionMethod;

        private String transactionId;
}
