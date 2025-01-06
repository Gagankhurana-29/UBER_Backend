package com.khuragag.project.uber.uber.entities.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {

    private Long id;
    private UserDTO userDTO;
    private Double rating;
    private String vehicleId;
    PointDTO currentLocation;

}
