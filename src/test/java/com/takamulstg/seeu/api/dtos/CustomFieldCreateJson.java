package com.takamulstg.seeu.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomFieldCreateJson {
    private String customFieldType;
    private Long maxValue;
    private Long minValue;
    private String name;
}
