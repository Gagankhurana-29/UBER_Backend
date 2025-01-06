package com.khuragag.project.uber.uber.services;

import com.khuragag.project.uber.uber.entities.DTO.DriverDTO;
import com.khuragag.project.uber.uber.entities.DTO.RideDTO;
import com.khuragag.project.uber.uber.entities.DTO.RideRequestDTO;
import com.khuragag.project.uber.uber.entities.DTO.RiderDTO;
import com.khuragag.project.uber.uber.entities.Rider;
import com.khuragag.project.uber.uber.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RiderService {

     RideRequestDTO requestRide(RideRequestDTO rideRequestDTO);

     RideDTO cancelRide(Long rideId);

     Page<RideDTO> myAllRides(PageRequest pageRequest);

     RiderDTO getMyProfile();

     DriverDTO rateDriver(Long rideId, Double rating);

     Rider createRider(User user);

     Rider getCurrentRider();

}
