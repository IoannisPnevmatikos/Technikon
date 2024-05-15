package com.team1.technikon.mapper;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.dto.PropertyDto;
import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.model.Owner;
import com.team1.technikon.model.Property;
import com.team1.technikon.model.Repair;
import org.mapstruct.*;

@Mapper(componentModel = "spring"  )
@MapperConfig(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TechnikonMapper {
    OwnerDto toOwnerDto(Owner owner);
    Owner toOwner(OwnerDto ownerDto);
//    @Mapping(target="owner", source = "updateOwnerDto", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    Owner toOwnerNotNull (OwnerDto updateOwnerDto);

    PropertyDto toPropertyDto(Property property);
    Property toProperty(PropertyDto propertyDto);

    RepairDto toRepairDto(Repair repair);
    Repair toRepair(RepairDto repairDto);

  //  @Mapping(target = "repair", source = "repairDto",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Repair toRepairNonNull(RepairDto repairDto);

 //   @Mapping(target = "property", source = "propertyDto", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Property toPropertyNoNull(PropertyDto propertyDto);
}
