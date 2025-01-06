package com.khuragag.project.uber.uber.services;


import org.locationtech.jts.geom.Point;

public interface DistanceService {

     double calculateDistance(Point startLocation, Point endLocation);

}
