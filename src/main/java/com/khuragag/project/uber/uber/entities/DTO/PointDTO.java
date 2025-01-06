package com.khuragag.project.uber.uber.entities.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PointDTO {
    private double[] coordinates;
    private String type = "Point";

    public PointDTO(double[] coordinates) {
        this.coordinates = coordinates;
    }
}
