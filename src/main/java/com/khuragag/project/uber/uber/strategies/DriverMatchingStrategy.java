package com.khuragag.project.uber.uber.strategies;

import com.khuragag.project.uber.uber.entities.Driver;
import com.khuragag.project.uber.uber.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {

    public List<Driver> getDrivers(RideRequest rideRequest);

}
