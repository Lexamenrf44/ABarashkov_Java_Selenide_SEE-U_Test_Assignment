package com.takamulstg.seeu.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AllContactsJson {
    private UUID id;
    private Object avatar;
    private String fullName;
    private String email;
    private String mobileNumber;
    private Object lists;
}
