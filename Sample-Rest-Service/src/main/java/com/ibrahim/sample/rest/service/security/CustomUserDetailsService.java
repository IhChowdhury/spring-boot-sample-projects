package com.ibrahim.sample.rest.service.security;

import com.ibrahim.sample.rest.service.exception.ResourceNotFoundException;
import com.ibrahim.sample.rest.service.model.entity.User;
import com.ibrahim.sample.rest.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            User user = userService.getUserByEmail(email);
            return new org.springframework.security.core.userdetails.User(email, user.getPassword(),new HashSet<>());
        } catch (ResourceNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

    public boolean isValidUser(String email, String password){
        UserDetails userDetails = loadUserByUsername(email);
        return passwordEncoder.matches(password, userDetails.getPassword());
    }
}
