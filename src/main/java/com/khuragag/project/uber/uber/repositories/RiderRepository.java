package com.khuragag.project.uber.uber.repositories;

import com.khuragag.project.uber.uber.entities.Rider;
import com.khuragag.project.uber.uber.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {
    Optional<Rider> findByUser(User user);
}
