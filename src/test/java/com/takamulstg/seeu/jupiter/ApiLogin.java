package com.takamulstg.seeu.jupiter;


import com.takamulstg.seeu.data.Password;
import com.takamulstg.seeu.data.Username;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ExtendWith(ApiLoginExtension.class)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiLogin {

    Username username() default Username.valid_username;
    Password password() default Password.valid_password;

}