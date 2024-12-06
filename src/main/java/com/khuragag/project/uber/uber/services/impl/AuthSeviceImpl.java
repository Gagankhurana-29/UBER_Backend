package com.khuragag.project.uber.uber.services.impl;

import com.khuragag.project.uber.uber.entities.DTO.DriverDTO;
import com.khuragag.project.uber.uber.entities.DTO.SignUPDTO;
import com.khuragag.project.uber.uber.entities.DTO.UserDTO;
import com.khuragag.project.uber.uber.services.AuthService;
import org.springframework.stereotype.Service;


@Service
public class AuthSeviceImpl implements AuthService {
    @Override
    public void signUpUser(SignUPDTO signUPDTO) {

    }

    @Override
    public String loginUser(String email, String password) {
        return "";
    }

    @Override
    public DriverDTO onBoardNewDriver(DriverDTO driver, UserDTO user) {
        return null;
    }
}
