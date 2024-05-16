package com.team1.technikon.repository;

import com.team1.technikon.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
   // List<Owner> findOwnersByActiveTrue();

    Optional<Owner> findByTinNumber(String tinNumber);

    @Transactional
    @Modifying
    @Query("update Owner o set o.address = :address where o.tinNumber = :tinNumber")
    int updateAddress(@Param("tinNumber") String tinNumber, @Param("address") String address);

   @Transactional
   @Modifying
//    @Query("delete Owner o where o.tinNumber = : tinNumber")
    void deleteByTinNumber(String tinNumber);


}
