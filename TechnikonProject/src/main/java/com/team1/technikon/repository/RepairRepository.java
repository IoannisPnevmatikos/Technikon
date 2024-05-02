package com.team1.technikon.repository;

import com.team1.technikon.model.Repair;
import com.team1.technikon.model.enums.StatusOfRepair;
import com.team1.technikon.model.enums.TypeOfRepair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {

    // SEARCHES
    @Query("select r from Repair r where r.localDateTime = :localDateTime")
    List<Repair> getRepairByDate(@Param("localDateTime") LocalDateTime localDateTime);

    @Query("select r from Repair r where r.localDateTime between :startingDate and :endingDate")
    List<Repair> getRepairByRangeOfDates(@Param("startingDate") LocalDateTime startingDate, @Param("endingDate") LocalDateTime endingTime);

    @Query("select r from Repair r where r.owner = :tinNumber")
    List<Repair> searchByOwnerTinNumber(@Param("tinNumber") long tinNumber);

    // UPDATES
    @Transactional
    @Modifying
    @Query("update Repair r set r.localDateTime = :localDateTime where  r.id = :id")
    int updateDate(@Param("id") long id, @Param("localDateTime") LocalDateTime localDateTime);

    @Transactional
    @Modifying
    @Query("update Repair r set r.shortDescription = :shortDescription where r.id = :id")
    int updateShortDescription(@Param("id") long id, @Param("shortDescription") String shortDescription);

    @Transactional
    @Modifying
    @Query("update Repair r set r.typeOfRepair = :typeOfRepair where r.id = :id")
    int updateTypeOfRepair(@Param("id") long id, @Param("typeOfRepair") TypeOfRepair typeOfRepair);

    @Transactional
    @Modifying
    @Query("update Repair r set r.statusOfRepair = :statusOfRepair where r.id = :id")
    int updateStatusOfRepair(@Param("id") long id, @Param("statusOfRepair") StatusOfRepair statusOfRepair);

    @Transactional
    @Modifying
    @Query("update Repair r set r.cost = :cost where r.id = :id")
    int updateCost(@Param("id") long id, @Param("cost") BigDecimal cost);

    @Transactional
    @Modifying
    @Query("update Repair r set r.descriptionText = :descriptionText where r.id = :id")
    int updateDescriptionText(@Param("id") long id, @Param("descriptionText") String descriptionText);

}
