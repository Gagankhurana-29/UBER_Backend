package com.khuragag.project.uber.uber.entities;

import com.khuragag.project.uber.uber.entities.enums.PaymentMethod;
import com.khuragag.project.uber.uber.entities.enums.RideStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {
        @Index(name = "idx_ride_rider", columnList = "rider_id"),
        @Index(name = "idx_ride_driver", columnList = "driver_id"),
        @Index(name = "idx_ride_rideStatus", columnList = "rideStatus")
})
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    private Rider rider;

    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

    @Enumerated(value = EnumType.STRING)
    private RideStatus rideStatus;

    @Column(columnDefinition = "Geometry(Point,4326)")
    Point pickupLocation;

    @Column(columnDefinition = "Geometry(Point,4326)")
    Point dropLocation;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime startTime;

    private  LocalDateTime endTime;

    private Double fare;

    private String Otp;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

}
