package com.khuragag.project.uber.uber.controllers;

import com.khuragag.project.uber.uber.entities.DTO.DriverDTO;
import com.khuragag.project.uber.uber.entities.DTO.SignUPDTO;
import com.khuragag.project.uber.uber.entities.DTO.UserDTO;
import com.khuragag.project.uber.uber.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "signUp/")
    public ResponseEntity<UserDTO> signUpUser(@RequestBody SignUPDTO signUPDTO){
        return new ResponseEntity<>(authService.signUpUser(signUPDTO), HttpStatus.CREATED);
    }

    @PostMapping("/onBoardNewDriver/{userId}/{vehicleId}")// other methods...
    public ResponseEntity<DriverDTO> onBoardNewDriver(@PathVariable Long userId,
                                                      @PathVariable String vehicleId){
        return new ResponseEntity<DriverDTO>(authService.onBoardNewDriver(userId,vehicleId)
        ,HttpStatus.OK);
    }



}
