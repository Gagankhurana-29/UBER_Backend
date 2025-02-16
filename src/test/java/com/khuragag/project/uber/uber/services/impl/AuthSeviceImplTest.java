package com.khuragag.project.uber.uber.services.impl;

import com.khuragag.project.uber.uber.entities.DTO.LoginRequestDTO;
import com.khuragag.project.uber.uber.entities.User;
import com.khuragag.project.uber.uber.repositories.DriverRepository;
import com.khuragag.project.uber.uber.repositories.UserRepository;
import com.khuragag.project.uber.uber.security.JWTService;
import com.khuragag.project.uber.uber.services.RiderService;
import com.khuragag.project.uber.uber.services.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthSeviceImplTest {

    @Spy
    private ModelMapper modelMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RiderService riderService;

    @Mock
    private WalletService walletService;

    @Mock
    private DriverRepository driverRepository;

    @Spy
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JWTService jwtService;

    @InjectMocks
    private AuthServiceImpl authServiceImpl;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("tester");
        user.setEmail("test@gmail.com");
        user.setPassword("password123");
    }

    @Test
    void testLogin_whenSuccess(){
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);
        when(jwtService.generateRefreshToken(user)).thenReturn("refreshToken");
        when(jwtService.generateAccessToken(user)).thenReturn("accessToken");

        String[] tokens = authServiceImpl.login(new LoginRequestDTO(user.getEmail(), user.getPassword()));

        assertThat(tokens).hasSize(2);
        assertThat(tokens[0]).isEqualTo("accessToken");
        assertThat(tokens[1]).isEqualTo("refreshToken");
    }

    void testSignUp_whenSuccess(){
        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encoded password");

    }


}