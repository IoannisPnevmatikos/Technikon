package com.team1.technikon.securityservice.service;

import com.team1.technikon.securityservice.dto.UserInfoDto;
import com.team1.technikon.securityservice.mapper.UserInfoMapper;
import com.team1.technikon.securityservice.model.UserInfo;
import com.team1.technikon.securityservice.repository.UserInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserInfoService implements UserDetailsService {
    private final UserInfoRepository repository;
    private final PasswordEncoder encoder;
    private final UserInfoMapper userInfoMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userDetail = repository.findByName(username);
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String addUser(UserInfoDto userInfoDto) {
        UserInfo userInfo = userInfoMapper.toUserInfo(userInfoDto);
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        userInfo.setRole("USER");
        repository.save(userInfo);
        return "User Added Successfully";
    }

    public String addAdmin(UserInfoDto userInfoDto) {
        UserInfo userInfo = userInfoMapper.toUserInfo(userInfoDto);
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        userInfo.setRole("ADMIN");
        repository.save(userInfo);
        return "User Added Successfully";
    }

}