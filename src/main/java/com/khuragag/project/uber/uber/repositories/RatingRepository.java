package com.khuragag.project.uber.uber.repositories;

import com.khuragag.project.uber.uber.entities.Driver;
import com.khuragag.project.uber.uber.entities.Rating;
import com.khuragag.project.uber.uber.entities.Ride;
import com.khuragag.project.uber.uber.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating,Long> {

    List<Rating> findByRider(Rider rider);
    List<Rating> findByDriver(Driver driver);


    Optional<Rating> findByRide(Ride ride);
}
