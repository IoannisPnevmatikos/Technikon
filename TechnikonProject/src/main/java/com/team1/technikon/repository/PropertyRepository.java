package com.team1.technikon.repository;

import com.team1.technikon.model.MapLocation;
import com.team1.technikon.model.Property;
import com.team1.technikon.model.enums.TypeOfProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    Optional<Property> findByPropertyId(String propertyId);
    List<Property> findByOwnerTinNumber(String tinNumber);

    @Query("select p from Property p where p.mapLocation =:mapLocation")
    List<Property> getPropertyByLocation(); // NA TO DOYME META

//    @Query("select p from Property p where p.owner.tinNumber = :tinNumber")
//    List<Property> getPropertyByOwnerTinNumber(@Param("tinNumber") long tinNumber);


//    @Transactional
//    @Modifying
//    @Query("update Property p set p.propertyId = :newPropertyId where p.propertyId = :currentPropertyId")
//    int updatePropertyId(@Param("currentPropertyId") long currentPropertyId, @Param("newPropertyId") long newPropertyId);
//
//    @Transactional
//    @Modifying
//    @Query("update Property p set p.address = :address where p.propertyId = :propertyId")
//    int updateAddress(@Param("propertyId") long propertyId, @Param("address") String address);
//
//    @Transactional
//    @Modifying
//    @Query("update Property p set p.yearOfConstruction = :yearOfConstruction where p.propertyId = :propertyId")
//    int updateYearOfConstruction(@Param("propertyId") long propertyId, @Param("yearOfConstruction") String yearOfConstruction);
//
//    @Transactional
//    @Modifying
//    @Query("update Property p set p.typeOfProperty = :typeOfProperty where p.propertyId = :propertyId")
//    int updatePropertyType(@Param("propertyId") long propertyId, @Param("typeOfProperty") TypeOfProperty typeOfProperty);
//
//    @Transactional
//    @Modifying
//    @Query("update Property p set p.photo = :photo where p.propertyId = :propertyId")
//    int updatePhoto(@Param("propertyId") long propertyId, @Param("photo") String photo);
//
//    @Transactional
//    @Modifying
//    @Query("update Property p set p.mapLocation = :mapLocation where p.propertyId = :propertyId")
//    int updateMapLocation(@Param("propertyId") long propertyId, @Param("mapLocation") MapLocation mapLocation);

}
