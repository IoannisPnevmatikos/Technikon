package com.team1.technikon.mapper;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.dto.PropertyDto;
import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.model.Owner;
import com.team1.technikon.model.Property;
import com.team1.technikon.model.Repair;

public class Mapper {

    public static OwnerDto mapToOwnerDto(Owner owner) {
        return new OwnerDto(
                owner.getTinNumber(),
                owner.getFirstName(),
                owner.getLastName(),
                owner.getUsername(),
                owner.getPassword(),
                owner.getEmail(),
                owner.getAddress(),
                owner.getPhone()
        );
    }

    public static Owner mapToOwner(OwnerDto ownerDto) {
        Owner owner = new Owner();
        owner.setTinNumber(ownerDto.tinNumber());
        owner.setAddress(ownerDto.address());
        owner.setPhone(ownerDto.phone());
        owner.setFirstName(ownerDto.firstName());
        owner.setLastName(ownerDto.lastName());
        owner.setUsername(ownerDto.username());
        owner.setPassword(ownerDto.password());
        owner.setEmail(ownerDto.email());
        return owner;
    }

    public static PropertyDto mapToPropertyDto(Property property) {
        return new PropertyDto(
                property.getPropertyId(),
                property.getAddress(),
                property.getYearOfConstruction(),
                property.getTypeOfProperty(),
                property.getPhoto(),
                property.getMapLocation(),
                property.getRegistrationDate(),
                property.isActive(),
                property.getOwner().getTinNumber()
        );
    }

    public static Property mapToProperty(PropertyDto propertyDto) {
        Property property = new Property();
        property.setPropertyId(propertyDto.propertyId());
        property.setAddress(propertyDto.address());
        property.setYearOfConstruction(propertyDto.yearOfConstruction());
        property.setTypeOfProperty(propertyDto.typeOfProperty());
        property.setPhoto(propertyDto.photo());
        property.setMapLocation(propertyDto.mapLocation());
        return property;
    }

    public static RepairDto mapToRepairDto(Repair repair) {
        return new RepairDto(
                repair.getId(),
                repair.getLocalDate(),
                repair.getShortDescription(),
                repair.getTypeOfRepair(),
                repair.getStatusOfRepair(),
                repair.getCost(),
                repair.getDescriptionText(),
                repair.getProperty().getPropertyId() // Return the property ID
        );
    }

    public static Repair mapToRepair(RepairDto repairDto) {
        Repair repair = new Repair();
        repair.setId(repairDto.repairId());
        repair.setLocalDate(repairDto.localDate());
        repair.setShortDescription(repairDto.shortDescription());
        repair.setTypeOfRepair(repairDto.typeOfRepair());
        repair.setStatusOfRepair(repairDto.statusOfRepair());
        repair.setCost(repairDto.cost());
        repair.setDescriptionText(repairDto.descriptionText());
        // Note: Property will be set in the service method after fetching it by ID
        return repair;
    }

}
