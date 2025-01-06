package com.khuragag.project.uber.uber.configs;

import com.khuragag.project.uber.uber.entities.DTO.PointDTO;
import com.khuragag.project.uber.uber.util.GeometryUtil;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {

      ModelMapper modelMapper =  new ModelMapper();

      // PointDTO to Point conversion
    modelMapper.typeMap(PointDTO.class, Point.class).setConverter(context ->{
        PointDTO pointDTO = context.getSource();
        return GeometryUtil.createGeometryPoint(pointDTO);
    });

    //Point to PoinntDTO class
      modelMapper.typeMap(Point.class, PointDTO.class).setConverter(context->{
          double xCord = context.getSource().getX();
          double yCord = context.getSource().getY();
          return new PointDTO(new double[]{xCord,yCord});
      });


    return modelMapper;
  }

}
