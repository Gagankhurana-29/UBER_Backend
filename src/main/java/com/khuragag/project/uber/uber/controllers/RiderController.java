package com.khuragag.project.uber.uber.controllers;

import com.khuragag.project.uber.uber.entities.DTO.*;
import com.khuragag.project.uber.uber.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rider")
@RequiredArgsConstructor
public class RiderController {

    private final RiderService riderService;

    @PostMapping("/requestRide")
    public ResponseEntity<RideRequestDTO> requestRide(@RequestBody RideRequestDTO rideRequestDTO){
      return ResponseEntity.ok(riderService.requestRide(rideRequestDTO));
    }

    @PostMapping("/cancelRide/{rideId}")
   public ResponseEntity<RideDTO> cancelRide(@PathVariable Long rideId){
        return ResponseEntity.ok(riderService.cancelRide(rideId));
   }

   @GetMapping("/getMyProfile")
    public ResponseEntity<RiderDTO> getMyProfile(){
        return ResponseEntity.ok(riderService.getMyProfile());
    }

   @PostMapping("/rateDriver")
   public ResponseEntity<DriverDTO> rateDriver(@RequestBody RatingDTO ratingDTO){
        return ResponseEntity.ok(riderService.rateDriver(ratingDTO.getRideId(), ratingDTO.getRating()));
   }

   @PostMapping("/getMyRides")
    public ResponseEntity<Page<RideDTO>> getMyRides(@RequestParam(defaultValue = "0") Integer pageOffSet,
                                                    @RequestParam(defaultValue = "10") Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageOffSet, pageSize);
        return ResponseEntity.ok(riderService.myAllRides(pageRequest));
   }

}
