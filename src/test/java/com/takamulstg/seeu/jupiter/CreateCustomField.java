package com.takamulstg.seeu.jupiter;

import com.takamulstg.seeu.data.CustomFieldType;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ExtendWith(CreateCustomFieldExtension.class)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CreateCustomField {
    CustomFieldType type();
}
