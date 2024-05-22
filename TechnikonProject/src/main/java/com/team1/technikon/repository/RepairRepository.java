package com.team1.technikon.repository;

import com.team1.technikon.model.Repair;
import com.team1.technikon.model.enums.StatusOfRepair;
import com.team1.technikon.model.enums.TypeOfRepair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {

    List<Repair> findByLocalDate(LocalDate localDate);

    @Query("SELECT r FROM Repair r WHERE r.property.owner.id = :ownerId")
    List<Repair> findByOwnerId(long ownerId);

//    List<Repair> findByOwnerTinNumber(String tinNumber);
    @Query("select r from Repair r where r.property.owner.tinNumber = :tinNumber")
    List<Repair> findByOwnerTinNumber(@Param("tinNumber") String tinNumber);

    List<Repair> findByLocalDateBetween(LocalDate startingDate, LocalDate endingDate);

}
