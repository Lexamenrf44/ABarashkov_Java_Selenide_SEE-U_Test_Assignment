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
public class GetAllCustomFieldsJson {
    private UUID id;
    private String name;
    private Integer customFieldType;
    private Long minValue;
    private Long maxValue;
    private Object acceptedType;
}
