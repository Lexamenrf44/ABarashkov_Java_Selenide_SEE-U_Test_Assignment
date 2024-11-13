package com.takamulstg.seeu.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProfileJson {
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String email;
    private Integer role;
    private Boolean isBtoC;
}
