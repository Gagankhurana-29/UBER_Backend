package com.khuragag.project.uber.uber.services.impl;

import com.khuragag.project.uber.uber.entities.DTO.RideRequestDTO;
import com.khuragag.project.uber.uber.entities.Driver;
import com.khuragag.project.uber.uber.entities.Ride;
import com.khuragag.project.uber.uber.entities.RideRequest;
import com.khuragag.project.uber.uber.entities.Rider;
import com.khuragag.project.uber.uber.entities.enums.RideRequestStatus;
import com.khuragag.project.uber.uber.entities.enums.RideStatus;
import com.khuragag.project.uber.uber.repositories.RideRepository;
import com.khuragag.project.uber.uber.services.RideRequestService;
import com.khuragag.project.uber.uber.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService  {

    private final ModelMapper modelMapper;
    private final RideRepository rideRepository;
    private final RideRequestService rideRequestService;

    @Override
    public Ride getRideById(Long id) {
        return rideRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Ride with ID ${id} not found"));
    }

    @Override
    public void matchWithDrivers(RideRequestDTO rideRequestDTO) {

    }

    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {

        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);
        Ride ride = modelMapper.map(rideRequest, Ride.class);
        ride.setRideStatus(RideStatus.CONFIRMED);
        ride.setDriver(driver);
        ride.setOtp(generateOTP());
        ride.setId(null);

        rideRequestService.update(rideRequest);
        rideRepository.save(ride);
        return ride;
    }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
       ride.setRideStatus(rideStatus);
        return rideRepository.save(ride);
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest)
    {
        return rideRepository.findByRider(rider,pageRequest);
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest) {
        return rideRepository.findByDriver(driver, pageRequest);
    }

    private String generateOTP(){
        Random random = new Random();
        int otp = random.nextInt(10000);
        return String.format("%04d",otp);
    }

}
