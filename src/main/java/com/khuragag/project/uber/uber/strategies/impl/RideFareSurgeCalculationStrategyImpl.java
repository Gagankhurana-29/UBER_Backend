package com.khuragag.project.uber.uber.strategies.impl;

import com.khuragag.project.uber.uber.entities.RideRequest;
import com.khuragag.project.uber.uber.services.DistanceService;
import com.khuragag.project.uber.uber.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareSurgeCalculationStrategyImpl implements RideFareCalculationStrategy {

    private final DistanceService distanceService;

    @Override
    public double calculateRidePrice(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(), rideRequest.getDropLocation());
        return distance*SURGE_RIDE_FARE_MULTIPILER;
    }
}
