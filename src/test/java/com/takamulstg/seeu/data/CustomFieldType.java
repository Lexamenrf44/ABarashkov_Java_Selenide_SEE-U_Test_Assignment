package com.takamulstg.seeu.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CustomFieldType {
    date(2),
    text(0),
    rating(1);

    private final int fieldNumberToType;
}
