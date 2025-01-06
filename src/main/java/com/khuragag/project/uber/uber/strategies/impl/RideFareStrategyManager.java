package com.khuragag.project.uber.uber.strategies.impl;

import com.khuragag.project.uber.uber.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RideFareStrategyManager {

    private final RideFareSurgeCalculationStrategyImpl rideFareSurgeCalculationStrategyImpl;
    private final RideFareCalculationStrategyImpl rideFareCalculationImpl;

    public RideFareCalculationStrategy fareStrategy(){
        int requestTimeHour = LocalDateTime.now().getHour();
        int earlyMorningHours = 8;
        int lateNightHours = 20;
        if(requestTimeHour < earlyMorningHours || requestTimeHour>lateNightHours){
            return rideFareSurgeCalculationStrategyImpl;
        }
        else{
            return  rideFareCalculationImpl;
        }
    }

}
