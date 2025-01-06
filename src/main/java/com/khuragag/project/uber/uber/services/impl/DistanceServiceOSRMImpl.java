package com.khuragag.project.uber.uber.services.impl;

import com.khuragag.project.uber.uber.services.DistanceService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {

    private static final String OSRMAPI = "http://router.project-osrm.org/route/v1/driving/";


    @Override
    public double calculateDistance(Point startLocation, Point endLocation) {
        try {
            String uri = startLocation.getX()+","+startLocation.getY()+
                    ";"+endLocation.getX()+","+endLocation.getY();
            ORSMResponseDTO osrmResponseDTO = RestClient.builder()
                    .baseUrl(OSRMAPI)
                    .build()
                    .get()
                    .uri(uri)
                    .retrieve().body(ORSMResponseDTO.class);

            return osrmResponseDTO.routes.get(0).getDistance() / 1000.0;
        }
        catch (Exception e){
            throw new RuntimeException("Error fetching OSRM Distance "+ e.getMessage());
        }
    }
}

@Data
class ORSMResponseDTO{
    List<OSRMRoute> routes;
}

@Data
class OSRMRoute {
    private double distance;
}


