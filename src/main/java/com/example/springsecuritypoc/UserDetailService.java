package com.example.springsecuritypoc;

public interface UserDetailService {

    UserDetail loadUserByUsername(String username) throws UsernameNotFoundException;
}
