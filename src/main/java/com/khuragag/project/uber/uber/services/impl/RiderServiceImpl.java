package com.khuragag.project.uber.uber.services.impl;

import com.khuragag.project.uber.uber.entities.*;
import com.khuragag.project.uber.uber.entities.DTO.DriverDTO;
import com.khuragag.project.uber.uber.entities.DTO.RideDTO;
import com.khuragag.project.uber.uber.entities.DTO.RideRequestDTO;
import com.khuragag.project.uber.uber.entities.DTO.RiderDTO;
import com.khuragag.project.uber.uber.entities.enums.RideRequestStatus;
import com.khuragag.project.uber.uber.entities.enums.RideStatus;
import com.khuragag.project.uber.uber.repositories.RideRequestRepository;
import com.khuragag.project.uber.uber.repositories.RiderRepository;
import com.khuragag.project.uber.uber.services.DriverService;
import com.khuragag.project.uber.uber.services.RatingService;
import com.khuragag.project.uber.uber.services.RideService;
import com.khuragag.project.uber.uber.services.RiderService;
import com.khuragag.project.uber.uber.strategies.impl.DriverMatchingStrategyManager;
import com.khuragag.project.uber.uber.strategies.impl.RideFareStrategyManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideFareStrategyManager rideFareStrategyManager;
    private final DriverMatchingStrategyManager driverMatchingStrategyManager;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final RideService rideService;
    private final DriverService driverService;
    private final RatingService ratingService;

    @Override
    @Transactional
    public RideRequestDTO requestRide(RideRequestDTO rideRequestDTO) {
        Rider rider = getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDTO, RideRequest.class);
        log.info("Ride Request Object is "+ rideRequest);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);
        rideRequest.setFare(rideFareStrategyManager.fareStrategy().calculateRidePrice(rideRequest));
        RideRequest savedRide = rideRequestRepository.save(rideRequest);
        List<Driver> drivers = driverMatchingStrategyManager.matchDrivers(rider.getRating()).getDrivers(rideRequest);
        for(Driver d : drivers){
            System.out.println("Drivers are " + d.toString());
        }
        return modelMapper.map(savedRide, RideRequestDTO.class);
    }

    @Override
    public RideDTO cancelRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Rider rider = getCurrentRider();
        if(!ride.getRider().equals(rider))
        {
            throw new RuntimeException("Rider doesn't own the ride");
        }
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride can't be cancelled, as it isn't confirmed");
        }
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        driverService.updateDriverAvailability(savedRide.getDriver(),true);
        return modelMapper.map(savedRide,RideDTO.class);
    }

    @Override
    public Page<RideDTO> myAllRides(PageRequest pageRequest) {
        Rider rider = getCurrentRider();
        return rideService.getAllRidesOfRider(rider,pageRequest)
                .map(ride -> modelMapper.map(ride, RideDTO.class));
    }

    @Override
    public RiderDTO getMyProfile()
    {
        return modelMapper.map(getCurrentRider(),RiderDTO.class);
    }

    @Override
    public DriverDTO rateDriver(Long rideId, Double rating) {
        Ride ride = rideService.getRideById(rideId);
        Rider rider = getCurrentRider();
        if(!ride.getRider().equals(rider))
        {
            throw new RuntimeException("Rider doesn't own the ride");
        }
        if(!ride.getRideStatus().equals(RideStatus.COMPLETED)){
            throw new RuntimeException("Ride can't be rated, as it isn't completed");
        }

        return ratingService.rateDriver(ride,rating);
    }

    @Override
    public Rider createRider(User user) {
        Rider rider = Rider.builder().user(user).rating(0.0).build();
        Rider savedRider = riderRepository.save(rider);
        return savedRider;
    }

    @Override
    public Rider getCurrentRider() {
        //TODO : get current Rider from spring security
       User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Rider rider =  riderRepository.findByUser(user).orElse(null);
        if(rider == null){
            throw new RuntimeException("Rider not present");
        }
        return  rider;
    }
}
