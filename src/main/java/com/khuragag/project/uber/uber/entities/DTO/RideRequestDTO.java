package com.khuragag.project.uber.uber.entities.DTO;

import com.khuragag.project.uber.uber.entities.enums.PaymentMethod;
import com.khuragag.project.uber.uber.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDTO {

    private Long id;

    PointDTO pickupLocation;

    PointDTO dropLocation;

    private RiderDTO rider;

    private LocalDateTime requestedTime;

    private PaymentMethod paymentMethod;

    private Double fare;

    private RideRequestStatus rideRequestStatus;

}
