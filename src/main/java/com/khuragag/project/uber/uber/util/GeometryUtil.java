package com.khuragag.project.uber.uber.util;

import com.khuragag.project.uber.uber.entities.DTO.PointDTO;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

public class GeometryUtil {
    public static Point createGeometryPoint(PointDTO pointDTO){
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(),4326);
        Coordinate coordinate = new Coordinate(pointDTO.getCoordinates()[0], pointDTO.getCoordinates()[1]);
        Point point = geometryFactory.createPoint(coordinate);
        return point;
    }
}
