package com.team1.technikon.securityservice.service;

import com.team1.technikon.securityservice.model.UserInfo;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserInfoDetails implements UserDetails {
    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;
    private final boolean accountNonExpired = true;
    private final boolean accountNonLocked = true;
    private final boolean credentialsNonExpired = true;
    private final boolean enabled = true;

    public UserInfoDetails(UserInfo userInfo) {
        username = userInfo.getUsername();
        password = userInfo.getPassword();
        authorities = Arrays.stream(userInfo.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}