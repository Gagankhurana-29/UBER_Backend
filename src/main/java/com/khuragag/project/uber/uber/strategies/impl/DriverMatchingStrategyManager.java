package com.khuragag.project.uber.uber.strategies.impl;

import com.khuragag.project.uber.uber.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverMatchingStrategyManager {

    private final DriversMatchingHighestRatedDriversStrategy driversMatchingHighestRatedDriversStrategy;
    private final DriversMatchingNearestDriverStrategy driversMatchingNearestDriverStrategy;
    private static final double selectBestRatedDriversThreshold = 4.5;

    public DriverMatchingStrategy matchDrivers(Double riderRating){
        if(riderRating >= selectBestRatedDriversThreshold){
           return driversMatchingHighestRatedDriversStrategy;
        }
        else{
            return  driversMatchingNearestDriverStrategy;
        }
    }

}
