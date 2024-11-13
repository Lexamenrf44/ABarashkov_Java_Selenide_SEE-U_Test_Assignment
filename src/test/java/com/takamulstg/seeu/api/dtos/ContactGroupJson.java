package com.takamulstg.seeu.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ContactGroupJson {
    private String color;
    private String name;
    private String listName;
    private Integer status;
    private Object contactAnalysisCampaignStatus;
    private Boolean hasRfi;
}
