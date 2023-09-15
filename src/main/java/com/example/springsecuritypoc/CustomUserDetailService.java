package com.example.springsecuritypoc;

import com.example.springsecuritypoc.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailService{

    private final UserRepository userRepository;

    @Override
    public UserDetail loadUserByUsername (String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username: " + username));
        return new User(user.getUsername(), user.getPassword());
    }




}
