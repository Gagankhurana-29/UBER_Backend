package com.khuragag.project.uber.uber.services.impl;

import com.khuragag.project.uber.uber.entities.DTO.DriverDTO;
import com.khuragag.project.uber.uber.entities.DTO.RideDTO;
import com.khuragag.project.uber.uber.entities.DTO.RiderDTO;
import com.khuragag.project.uber.uber.entities.Driver;
import com.khuragag.project.uber.uber.entities.Ride;
import com.khuragag.project.uber.uber.entities.RideRequest;
import com.khuragag.project.uber.uber.entities.User;
import com.khuragag.project.uber.uber.entities.enums.RideRequestStatus;
import com.khuragag.project.uber.uber.entities.enums.RideStatus;
import com.khuragag.project.uber.uber.exceptions.ResourceNotfoundException;
import com.khuragag.project.uber.uber.repositories.DriverRepository;
import com.khuragag.project.uber.uber.repositories.RideRepository;
import com.khuragag.project.uber.uber.services.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final PaymentService paymentService;
    private final RideService rideService;
    private final ModelMapper modelMapper;
    private final RideRepository rideRepository;
    private final RatingService ratingService;

    @Override
    @Transactional
    public RideDTO acceptRide(Long rideRequestId) {
        RideRequest  rideRequest= rideRequestService.findRideRequestById(rideRequestId);
        if(! rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING))
        {
            throw new RuntimeException("Ride Request is already been processed");
        }
        Driver driver = getCurrentDriver();
        if(!driver.getAvailable()){
            throw new ResourceNotfoundException("Driver with id 2 is unavailable");
        }
        Driver updatedDriver = updateDriverAvailability(driver,false);
        Ride ride = rideService.createNewRide(rideRequest,updatedDriver);
        return modelMapper.map(ride, RideDTO.class);
    }

    @Override
    @Transactional
    public RideDTO cancelRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if(!ride.getDriver().equals(driver)){
            throw new RuntimeException("Current Driver is not matching with ride Driver");
        }

        if(ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            rideService.updateRideStatus(ride,RideStatus.CANCELLED);
            updateDriverAvailability(driver, true);
            return modelMapper.map(ride,RideDTO.class);
        }
        else{
            throw new RuntimeException("Ride is already started, it can't be cancel now");
        }
    }

    @Override
    public RideDTO startRide(Long rideId, String rideStartOTP)
    {
       Ride ride =  rideService.getRideById(rideId);
       if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
           throw new RuntimeException("Ride is not confirmed for RideID " + rideId);
       }
       if(!ride.getDriver().equals(getCurrentDriver())){
           throw new RuntimeException("Current Driver is not matching with ride Driver");
       }
       if(!ride.getOtp().equals(rideStartOTP)){
           System.out.println("Ride otp "+ ride.getOtp());
           System.out.println("Ride otp "+ rideStartOTP);
            throw new RuntimeException("otp doesn't match with ride otp");
       }

       paymentService.createNewPayment(ride);
       ratingService.createNewRating(ride);
       ride.setStartTime(LocalDateTime.now());
       rideService.updateRideStatus(ride,RideStatus.STARTED);
       return modelMapper.map(ride, RideDTO.class);
    }

    @Override
    public RideDTO endRide(Long rideId) {
        Ride ride =  rideService.getRideById(rideId);
        if(!ride.getRideStatus().equals(RideStatus.STARTED)){
            throw new RuntimeException("Ride is not started for RideID " + rideId);
        }
        if(!ride.getDriver().equals(getCurrentDriver())){
            throw new RuntimeException("Current Driver is not matching with ride Driver");
        }

        ride.setEndTime(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.COMPLETED);
        paymentService.processPayment(savedRide);
        updateDriverAvailability(getCurrentDriver(),true);

        return modelMapper.map(savedRide, RideDTO.class);
    }

    @Override
    public RiderDTO rateRider(Long rideId, Double rating) {
        Ride ride = rideService.getRideById(rideId);
        if(!ride.getRideStatus().equals(RideStatus.COMPLETED)){
            throw new RuntimeException("Ride is not completed to rate for RideID " + rideId);
        }
        if(!ride.getDriver().equals(getCurrentDriver())){
            throw new RuntimeException("Current Driver is not matching with ride Driver");
        }
        return ratingService.rateRider(ride,rating);
    }

    @Override
    public DriverDTO getMyProfile() {
        Driver driver = getCurrentDriver();
        return modelMapper.map(driver, DriverDTO.class);
    }

    @Override
    public Page<RideDTO> getAllMyRides(PageRequest pageRequest)
    {
        Driver driver = getCurrentDriver();
       return rideService.getAllRidesOfDriver(driver,pageRequest).
                map(ride -> modelMapper.map(ride,RideDTO.class));
    }

    @Override
    public Driver getCurrentDriver() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return driverRepository.findByUser(user).
                orElseThrow(() -> new ResourceNotfoundException("Driver not present with user " + user ));
    }

    @Override
    public Driver updateDriverAvailability(Driver driver,Boolean availabilty) {
        driver.setAvailable(availabilty);
        return driverRepository.save(driver);
    }
}
