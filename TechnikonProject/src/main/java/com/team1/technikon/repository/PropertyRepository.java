package com.team1.technikon.repository;

import com.team1.technikon.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    Optional<Property> findByPropertyId(String propertyId);

    List<Property> findByRegistrationDateBetween(LocalDate startDate, LocalDate endDate);

    List<Property> findByOwnerTinNumber(String tinNumber);

}
