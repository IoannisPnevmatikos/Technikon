package com.team1.technikon.mapper;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.dto.PropertyDto;
import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.model.Owner;
import com.team1.technikon.model.Property;
import com.team1.technikon.model.Repair;

public class MapperTemp {

    public static OwnerDto mapToOwnerDto(Owner owner) {
        return new OwnerDto(
                owner.getTinNumber(),
                owner.getAddress(),
                owner.getPhone(),
                owner.getUserInfo()
        );
    }

    public static Owner mapToOwner(OwnerDto ownerDto) {
        Owner owner = new Owner();
        owner.setTinNumber(ownerDto.tinNumber());
        owner.setAddress(ownerDto.address());
        owner.setPhone(ownerDto.phone());
        owner.setUserInfo(ownerDto.userInfo());
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
                property.getOwner()
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
        property.setOwner(propertyDto.owner());
        return property;
    }

    public static RepairDto mapToRepairDto(Repair repair) {
        return new RepairDto(
                repair.getLocalDate(),
                repair.getShortDescription(),
                repair.getTypeOfRepair(),
                repair.getStatusOfRepair(),
                repair.getCost(),
                repair.getDescriptionText(),
                repair.getProperty()
        );
    }

    public static Repair mapToRepair(RepairDto repairDto) {
        Repair repair = new Repair();
        repair.setLocalDate(repairDto.localDate());
        repair.setShortDescription(repairDto.shortDescription());
        repair.setTypeOfRepair(repairDto.typeOfRepair());
        repair.setStatusOfRepair(repairDto.statusOfRepair());
        repair.setCost(repairDto.cost());
        repair.setDescriptionText(repairDto.descriptionText());
        repair.setProperty(repairDto.property());
        return repair;
    }

    public static Repair mapToRepairNonNull(RepairDto repairDto) {
        Repair repair = new Repair();
        repair.setLocalDate(repairDto.localDate());
        repair.setShortDescription(repairDto.shortDescription());
        repair.setTypeOfRepair(repairDto.typeOfRepair());
        repair.setStatusOfRepair(repairDto.statusOfRepair());
        repair.setCost(repairDto.cost());
        repair.setDescriptionText(repairDto.descriptionText());
        repair.setProperty(repairDto.property());
        return repair;
    }

    public static Property mapToPropertyNoNull(PropertyDto propertyDto) {
        Property property = new Property();
        if (propertyDto.propertyId()!=null) property.setPropertyId(propertyDto.propertyId());
        if (propertyDto.address()!=null) property.setAddress(propertyDto.address());
        if (propertyDto.yearOfConstruction()!=null) property.setYearOfConstruction(propertyDto.yearOfConstruction());
        if (propertyDto.typeOfProperty()!=null) property.setTypeOfProperty(propertyDto.typeOfProperty());
        if (propertyDto.photo()!=null) property.setPhoto(propertyDto.photo());
        if (propertyDto.mapLocation()!=null) property.setMapLocation(propertyDto.mapLocation());
        if (propertyDto.owner()!=null) property.setOwner(propertyDto.owner());
        return property;
    }

}
