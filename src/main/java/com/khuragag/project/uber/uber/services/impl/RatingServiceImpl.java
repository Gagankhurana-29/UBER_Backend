package com.khuragag.project.uber.uber.services.impl;

import com.khuragag.project.uber.uber.entities.DTO.DriverDTO;
import com.khuragag.project.uber.uber.entities.DTO.RiderDTO;
import com.khuragag.project.uber.uber.entities.Driver;
import com.khuragag.project.uber.uber.entities.Rating;
import com.khuragag.project.uber.uber.entities.Ride;
import com.khuragag.project.uber.uber.entities.Rider;
import com.khuragag.project.uber.uber.repositories.DriverRepository;
import com.khuragag.project.uber.uber.repositories.RatingRepository;
import com.khuragag.project.uber.uber.repositories.RiderRepository;
import com.khuragag.project.uber.uber.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final ModelMapper modelMapper;
    @Override
    public DriverDTO rateDriver(Ride ride, Double rating) {
        Driver driver = ride.getDriver();
        Rating ratingObj = ratingRepository.findByRide(ride)
                .orElseThrow(()-> new RuntimeException("Could not find rating object"));

        ratingObj.setDriverRating(rating);

        ratingRepository.save(ratingObj);

        Double newRating = ratingRepository
                .findByDriver(driver)
                .stream().mapToDouble(Rating :: getDriverRating)
                .average().orElse(0.0);

        driver.setRating(newRating);
        Driver savedDriver = driverRepository.save(driver);
        return modelMapper.map(savedDriver, DriverDTO.class);
    }

    @Override
    public RiderDTO rateRider(Ride ride, Double rating) {
        Rider rider = ride.getRider();
        Rating ratingObj = ratingRepository.findByRide(ride)
                .orElseThrow(()-> new RuntimeException("Could not find rating object"));

        ratingObj.setRiderRating(rating);

        ratingRepository.save(ratingObj);

        Double newRating = ratingRepository
                .findByRider(rider)
                .stream().mapToDouble(Rating :: getDriverRating)
                .average().orElse(0.0);

        rider.setRating(newRating);
        Rider savedRider = riderRepository.save(rider);
        return modelMapper.map(savedRider, RiderDTO.class);
    }

    @Override
    public void createNewRating(Ride ride) {
        Rating ratingObj = Rating.builder()
                .ride(ride)
                .rider(ride.getRider())
                .driver(ride.getDriver())
                .build();

        ratingRepository.save(ratingObj);
    }
}
