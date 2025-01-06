package com.khuragag.project.uber.uber.strategies.impl;

import com.khuragag.project.uber.uber.entities.Driver;
import com.khuragag.project.uber.uber.entities.RideRequest;
import com.khuragag.project.uber.uber.repositories.DriverRepository;
import com.khuragag.project.uber.uber.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriversMatchingNearestDriverStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> getDrivers(RideRequest rideRequest) {
        return driverRepository.getNearestDrivers(rideRequest.getPickupLocation());
    }
}
