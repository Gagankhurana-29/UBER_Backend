package com.khuragag.project.uber.uber.entities.DTO;

import com.khuragag.project.uber.uber.entities.enums.PaymentMethod;
import com.khuragag.project.uber.uber.entities.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideDTO {

    private Long id;

    private RiderDTO rider;

    private DriverDTO driver;

    private RideStatus rideStatus;

    PointDTO pickupLocation;

    PointDTO dropLocation;

    private LocalDateTime createdAt;

    private LocalDateTime startTime;

    private  LocalDateTime endTime;

    private Double fare;

    private String Otp;

    private PaymentMethod paymentMethod;

}
