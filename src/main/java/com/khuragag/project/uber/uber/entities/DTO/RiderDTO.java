package com.khuragag.project.uber.uber.entities.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RiderDTO {

        private Long id;

        private Double rating;

        private UserDTO user;

        PointDTO currentLocation;


}
