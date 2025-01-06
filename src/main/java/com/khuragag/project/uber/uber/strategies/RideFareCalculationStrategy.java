package com.khuragag.project.uber.uber.strategies;

import com.khuragag.project.uber.uber.entities.RideRequest;

public interface RideFareCalculationStrategy {

      static double RIDE_FARE_MULTIPLIER = 10;
      static double SURGE_RIDE_FARE_MULTIPILER = 15;
     double calculateRidePrice(RideRequest rideRequest);
}
