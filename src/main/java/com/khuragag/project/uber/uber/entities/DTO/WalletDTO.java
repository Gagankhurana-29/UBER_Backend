package com.khuragag.project.uber.uber.entities.DTO;

import com.khuragag.project.uber.uber.entities.User;
import com.khuragag.project.uber.uber.entities.WalletTransaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletDTO {

    private Long id;

    private User user;

    private Double balance;

    private List<WalletTransaction> transactions;
}
