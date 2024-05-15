package com.team1.technikon.repository;

import com.team1.technikon.model.Repair;
import com.team1.technikon.model.enums.StatusOfRepair;
import com.team1.technikon.model.enums.TypeOfRepair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {

    Optional<Repair> findByLocalDate(LocalDate localDate);

    List<Repair> findByPropertyOwnerTinNumber(String tinNumber);

    List<Repair> findByLocalDateBetween(LocalDate startingDate, LocalDate endingDate);

    // NOT USED - CAN DELETE THEM
    Optional<Repair> findByShortDescription(String shortDescription);

    Optional<Repair> findByTypeOfRepair(TypeOfRepair typeOfRepair);

    Optional<Repair> findByStatusOfRepair(StatusOfRepair statusOfRepair);

    Optional<Repair> findByCost(BigDecimal cost);

    Optional<Repair> findByDescriptionText(String descriptionText);

}
