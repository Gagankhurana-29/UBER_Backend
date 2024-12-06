package com.khuragag.project.uber.uber.services;


import com.khuragag.project.uber.uber.entities.DTO.DriverDTO;
import com.khuragag.project.uber.uber.entities.DTO.SignUPDTO;
import com.khuragag.project.uber.uber.entities.DTO.UserDTO;

public interface AuthService {

    void signUpUser(SignUPDTO signUPDTO);

    String loginUser(String email, String password);

    DriverDTO onBoardNewDriver(DriverDTO driver, UserDTO user);

}
