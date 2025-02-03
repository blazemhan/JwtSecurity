package com.blazemhan.demosecurity.service;

import com.blazemhan.demosecurity.entity.CustomUserDetails;
import com.blazemhan.demosecurity.entity.User;
import com.blazemhan.demosecurity.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
public class CustomUserDetailsService  implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(Objects.isNull(user)){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
}
