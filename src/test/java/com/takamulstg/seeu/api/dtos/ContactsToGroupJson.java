package com.takamulstg.seeu.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ContactsToGroupJson {
    private List<UUID> contactsIds;
    private UUID from;
    private UUID to;
}
