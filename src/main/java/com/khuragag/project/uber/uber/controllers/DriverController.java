package com.khuragag.project.uber.uber.controllers;

import com.khuragag.project.uber.uber.entities.DTO.*;
import com.khuragag.project.uber.uber.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/driver/acceptRide/{rideRequestID}")
    public ResponseEntity<RideDTO> acceptRide(@PathVariable Long rideRequestID){
        return ResponseEntity.ok(driverService.acceptRide(rideRequestID));
    }

    @PostMapping("/driver/startRide/{rideId}")
    public ResponseEntity<RideDTO> startRide(@PathVariable Long rideId,
                                             @RequestBody RideStartDTO rideStartDTO){
        return ResponseEntity.ok(driverService.startRide(rideId, rideStartDTO.getOtp()));
    }

    @PostMapping("/driver/endRide/{rideId}")
    public ResponseEntity<RideDTO> endRide(@PathVariable Long rideId){
        return ResponseEntity.ok(driverService.endRide(rideId));
    }

    @PostMapping("/cancelRide/{rideId}")
    public ResponseEntity<RideDTO> cancelRide(@PathVariable Long rideId){
        return ResponseEntity.ok(driverService.cancelRide(rideId));
    }

    @GetMapping("/getMyProfile")
    public ResponseEntity<DriverDTO> getMyProfile(){
        return ResponseEntity.ok(driverService.getMyProfile());
    }

    @PostMapping("/rateDriver")
    public ResponseEntity<RiderDTO> rateDriver(@RequestBody RatingDTO ratingDTO){
        return ResponseEntity.ok(driverService.rateRider(ratingDTO.getRideId(), ratingDTO.getRating()));
    }

    @PostMapping("/getMyRides")
    public ResponseEntity<Page<RideDTO>> getMyRides(@RequestParam(defaultValue = "0") Integer pageOffSet,
                                                    @RequestParam(defaultValue = "10") Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageOffSet, pageSize);
        return ResponseEntity.ok(driverService.getAllMyRides(pageRequest));
    }


}
