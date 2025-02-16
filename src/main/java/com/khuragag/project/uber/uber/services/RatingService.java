package com.khuragag.project.uber.uber.services;

import com.khuragag.project.uber.uber.entities.DTO.DriverDTO;
import com.khuragag.project.uber.uber.entities.DTO.RideDTO;
import com.khuragag.project.uber.uber.entities.DTO.RiderDTO;
import com.khuragag.project.uber.uber.entities.Ride;

public interface RatingService {

    DriverDTO rateDriver(Ride ride, Double rating);

    RiderDTO rateRider(Ride ride, Double rating);

    void createNewRating(Ride ride);
}
