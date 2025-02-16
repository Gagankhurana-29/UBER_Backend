package com.khuragag.project.uber.uber.controllers;

import com.khuragag.project.uber.uber.entities.DTO.*;
import com.khuragag.project.uber.uber.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    @PostMapping("/login/")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO,
                                                  HttpServletResponse httpServletResponse){
        String[] tokens = authService.login(loginRequestDTO);
        Cookie cookie = new Cookie("token", tokens[1]);
        cookie.setHttpOnly(true);
        httpServletResponse.addCookie(cookie);
        return ResponseEntity.ok(new LoginResponseDTO(tokens[0]));
    }

}
