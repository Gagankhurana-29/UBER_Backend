package com.khuragag.project.uber.uber.repositories;

import com.khuragag.project.uber.uber.entities.Ride;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
}
