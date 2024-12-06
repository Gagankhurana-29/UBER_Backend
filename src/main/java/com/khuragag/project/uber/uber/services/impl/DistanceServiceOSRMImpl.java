package com.khuragag.project.uber.uber.services.impl;

import com.khuragag.project.uber.uber.services.DistanceService;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service

public class DistanceServiceOSRMImpl implements DistanceService {
    @Override
    public double calculateDistance(Point startLocation, Point endLocation) {
        return 0;
    }
}
