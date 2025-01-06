package com.khuragag.project.uber.uber.services;

import com.khuragag.project.uber.uber.entities.DTO.RideRequestDTO;
import com.khuragag.project.uber.uber.entities.Driver;
import com.khuragag.project.uber.uber.entities.Ride;
import com.khuragag.project.uber.uber.entities.RideRequest;
import com.khuragag.project.uber.uber.entities.Rider;
import com.khuragag.project.uber.uber.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideById(Long id);

    void matchWithDrivers(RideRequestDTO rideRequestDTO);

    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);
}
