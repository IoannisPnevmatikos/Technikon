package com.team1.technikon.securityservice.service;

import com.team1.technikon.model.Owner;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserInfoDetails implements UserDetails {
    private final long id;
    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;
    private final boolean accountNonExpired = true;
    private final boolean accountNonLocked = true;
    private final boolean credentialsNonExpired = true;
    private final boolean enabled = true;

    public UserInfoDetails(Owner owner) {
        id = owner.getId();
        username = owner.getUsername();
        password = owner.getPassword();
        authorities = Arrays.stream(owner.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}