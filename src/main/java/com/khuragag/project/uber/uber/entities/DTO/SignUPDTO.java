package com.khuragag.project.uber.uber.entities.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUPDTO {
    private String name;
    private String email;
        private String password;
}
