package com.team1.technikon.securityservice.mapper;

import com.team1.technikon.securityservice.dto.UserInfoDto;
import com.team1.technikon.securityservice.model.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring"  )
@MapperConfig(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserInfoMapper {
    
 //   UserInfo toUserInfo(UserInfoDto userInfoDto);
    
}
