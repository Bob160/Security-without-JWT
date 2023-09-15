package com.example.springsecuritypoc;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class UserLoginRequest {

    //Collection<? extends GrantedAuthority> getAuthorities;

    private String Password;

    private String Username;
}
