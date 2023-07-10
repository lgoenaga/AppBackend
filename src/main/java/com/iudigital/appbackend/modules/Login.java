package com.iudigital.appbackend.modules;


import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Login {

    private String username;
    private String password;


}
