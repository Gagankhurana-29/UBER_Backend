package com.khuragag.project.uber.uber.repositories;

import com.khuragag.project.uber.uber.entities.Payment;
import com.khuragag.project.uber.uber.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByRide(Ride ride);
}
