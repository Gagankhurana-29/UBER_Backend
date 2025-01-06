package com.khuragag.project.uber.uber.entities.DTO;

import com.khuragag.project.uber.uber.entities.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private Set<Roles> roles;

}
