package com.khuragag.project.uber.uber.services.impl;

import com.khuragag.project.uber.uber.entities.DTO.DriverDTO;
import com.khuragag.project.uber.uber.entities.DTO.LoginRequestDTO;
import com.khuragag.project.uber.uber.entities.DTO.SignUPDTO;
import com.khuragag.project.uber.uber.entities.DTO.UserDTO;
import com.khuragag.project.uber.uber.entities.Driver;
import com.khuragag.project.uber.uber.entities.User;
import com.khuragag.project.uber.uber.entities.enums.Roles;
import com.khuragag.project.uber.uber.exceptions.ResourceNotfoundException;
import com.khuragag.project.uber.uber.repositories.DriverRepository;
import com.khuragag.project.uber.uber.repositories.UserRepository;
import com.khuragag.project.uber.uber.security.JWTService;
import com.khuragag.project.uber.uber.services.AuthService;
import com.khuragag.project.uber.uber.services.RiderService;
import com.khuragag.project.uber.uber.services.WalletService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverRepository driverRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    @Transactional
    public UserDTO signUpUser(SignUPDTO signUPDTO) {
       User user =  userRepository.findByEmail(signUPDTO.getEmail()).orElse(null);
       if(user != null){
           new RuntimeException(" User already exists with email id "
                   + signUPDTO.getEmail());
       }
        User mappedUser = modelMapper.map(signUPDTO, User.class);
        mappedUser.setRoles(Set.of(Roles.RIDER));
        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
        User savedUser = userRepository.save(mappedUser);

        //Create a rider
        riderService.createRider(savedUser);

        //TODO create wallet here
        walletService.createNewWallet(savedUser);

        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public String loginUser(String email, String password) {
        return "";
    }

    @Override
    public DriverDTO onBoardNewDriver(Long userId, String vehicleId) {
        User onBoardedDriver = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotfoundException("USer not present with id "+ userId));

        if(onBoardedDriver.getRoles().contains(Roles.DRIVER)){
            throw new RuntimeException("User is already a driver");
        }

        onBoardedDriver.getRoles().add(Roles.DRIVER);
        userRepository.save(onBoardedDriver);
        Driver driver = Driver
                .builder()
                .id(userId)
                .rating(0.0)
                .vehicleId(vehicleId)
                .build();
        Driver savedDriver = driverRepository.save(driver);
        return modelMapper.map(savedDriver, DriverDTO.class);
    }

    @Override
    public String[] login(LoginRequestDTO loginRequestDTO) {
       Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getEmail(),loginRequestDTO.getPassword()));
        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new String[]{accessToken,refreshToken};
    }
}
