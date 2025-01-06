package com.khuragag.project.uber.uber.entities;

import com.khuragag.project.uber.uber.entities.enums.PaymentMethod;
import com.khuragag.project.uber.uber.entities.enums.RideRequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(indexes = {
        @Index(name = "idx_rideRequest_rider" , columnList = "rider_id")
})
public class RideRequest{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    Point pickupLocation;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    Point dropLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    private Rider rider;

    @CreationTimestamp
    private LocalDateTime requestedTime;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private Double fare;

    @Enumerated(EnumType.STRING)
    private RideRequestStatus rideRequestStatus;

}
