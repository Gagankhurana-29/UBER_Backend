package com.khuragag.project.uber.uber.services;

import com.khuragag.project.uber.uber.entities.DTO.DriverDTO;
import com.khuragag.project.uber.uber.entities.DTO.RideDTO;
import com.khuragag.project.uber.uber.entities.DTO.RideRequestDTO;
import com.khuragag.project.uber.uber.entities.DTO.RiderDTO;

import java.util.List;

public interface RiderService {

     RideRequestDTO requestRide(RideRequestDTO rideRequestDTO);

     RideRequestDTO cancelRequest(RideRequestDTO rideRequestDTO);

     List<RideDTO> myAllRides();

     RiderDTO getMyProfile();

     DriverDTO rateDriver(Long rideId, Integer rating);

}
