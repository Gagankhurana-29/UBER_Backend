package com.khuragag.project.uber.uber.services;

import com.khuragag.project.uber.uber.entities.User;
import com.khuragag.project.uber.uber.repositories.UserRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
   private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElse(null);
    }

    public User getUserById(Long Id){
        return userRepository.findById(Id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
