package com.khuragag.project.uber.uber.services;


import com.khuragag.project.uber.uber.entities.RideRequest;

public interface RideRequestService {

    RideRequest findRideRequestById(Long rideRequestId);

    void update(RideRequest rideRequest);
}
