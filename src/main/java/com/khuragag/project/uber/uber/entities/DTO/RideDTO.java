package com.khuragag.project.uber.uber.entities.DTO;

import com.khuragag.project.uber.uber.entities.Driver;
import com.khuragag.project.uber.uber.entities.Rider;
import com.khuragag.project.uber.uber.entities.enums.PaymentMethod;
import com.khuragag.project.uber.uber.entities.enums.RideStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
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

    Point pickupLocation;

    Point dropLocation;

    private LocalDateTime createdAt;

    private LocalDateTime startTime;

    private  LocalDateTime endTime;

    private Double fare;

    private String Otp;

    private PaymentMethod paymentMethod;

}
