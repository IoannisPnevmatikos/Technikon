package com.team1.technikon.mapper;
import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.model.Owner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" )
public interface ObjectMapper {
    OwnerDto toOwnerDto(Owner owner);
    Owner toOwner(OwnerDto ownerDto);

}
