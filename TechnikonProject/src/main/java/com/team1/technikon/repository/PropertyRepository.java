package com.team1.technikon.repository;

import com.team1.technikon.model.MapLocation;
import com.team1.technikon.model.Property;
import com.team1.technikon.model.enums.TypeOfProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query("select p from Property p where p.owner = :tinNumber")
    List<Property> getPropertyByOwnerTinNumber(long tinNumber);

    @Query("select p from Property p where p.mapLocation =:mapLocation")
    List<Property> getPropertyByLocation();

    @Transactional
    @Modifying
    @Query("update Property p set p.propertyId = :newPropertyId where p.propertyId = :currentPropertyId")
    int updatePropertyId(long currentPropertyId, long newPropertyId);

    @Transactional
    @Modifying
    @Query("update Property p set p.address = :address where p.propertyId = :propertyId")
    int updateAddress(long propertyId, String address);

    @Transactional
    @Modifying
    @Query("update Property p set p.yearOfConstruction = :yearOfConstruction where p.propertyId = :propertyId")
    int updateYearOfConstruction(long propertyId, String yearOfConstruction);

    @Transactional
    @Modifying
    @Query("update Property p set p.typeOfProperty = :typeOfProperty where p.propertyId = :propertyId")
    int updatePropertyType(long propertyId, TypeOfProperty typeOfProperty);

    @Transactional
    @Modifying
    @Query("update Property p set p.photo = :photo where p.propertyId = :propertyId")
    int updatePhoto(long propertyId, String photo);

    @Transactional
    @Modifying
    @Query("update Property p set p.mapLocation = :mapLocation where p.propertyId = :propertyId")
    int updateMapLocation(long propertyId, MapLocation mapLocation);

}
