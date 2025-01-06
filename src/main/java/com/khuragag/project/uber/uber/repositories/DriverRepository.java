package com.khuragag.project.uber.uber.repositories;

import com.khuragag.project.uber.uber.entities.Driver;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    @Query(value = "Select d.* , ST_Distance(d.current_location, :pickupLoc) as distance "+
    "FROM driver d WHERE d.available=true AND ST_DWithin(d.current_location, :pickupLoc, 10000) "+
    "ORDER BY distance "+
            "LIMIT 10",
            nativeQuery = true)
     List<Driver> getNearestDrivers(Point pickupLoc);

    @Query(value = "Select d.* FROM driver d WHERE " +
            "d.available=true AND ST_DWithin(d.current_location, :pickupLocation, 10000) " +
            "ORDER BY d.rating DESC " +
            "LIMIT 10", nativeQuery = true
    )
    List<Driver> getTopRatedDrivers(Point pickupLocation);
}
