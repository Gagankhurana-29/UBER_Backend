package com.khuragag.project.uber.uber.services.impl;

import com.khuragag.project.uber.uber.entities.RideRequest;
import com.khuragag.project.uber.uber.exceptions.ResourceNotfoundException;
import com.khuragag.project.uber.uber.repositories.RideRequestRepository;
import com.khuragag.project.uber.uber.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;

    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository.findById(rideRequestId)
                .orElseThrow(()-> new RuntimeException("No ride Request with this id " + rideRequestId));
    }

    @Override
    public void update(RideRequest rideRequest) {
        rideRequestRepository.findById(rideRequest.getId())
                .orElseThrow(()->
                        new ResourceNotfoundException("Ride Request doesn't exist by id "+ rideRequest.getId()));

        rideRequestRepository.save(rideRequest);
    }
}
