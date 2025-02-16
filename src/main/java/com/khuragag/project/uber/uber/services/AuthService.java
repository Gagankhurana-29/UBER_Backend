package com.khuragag.project.uber.uber.services;


import com.khuragag.project.uber.uber.entities.DTO.DriverDTO;
import com.khuragag.project.uber.uber.entities.DTO.LoginRequestDTO;
import com.khuragag.project.uber.uber.entities.DTO.SignUPDTO;
import com.khuragag.project.uber.uber.entities.DTO.UserDTO;

public interface AuthService {

    UserDTO signUpUser(SignUPDTO signUPDTO);

    String loginUser(String email, String password);

    DriverDTO onBoardNewDriver(Long userId, String vehicleId);

    String[] login(LoginRequestDTO loginRequestDTO);
}
