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

    @Query("select o from Owner o where o.email = :email")
    Optional<Owner> getOwnerByEmail(String email);

    @Query("select o from Owner o where o.username = :username")
    Optional<Owner> getOwnerByUsername(String username);

    @Transactional
    @Modifying
    @Query("update Owner o set o.address = :address where o.tinNumber = :tinNumber")
    int updateAddress(@Param("tinNumber") String tinNumber, @Param("address") String address);

    @Transactional
    @Modifying
    @Query("update Owner o set o.email = :email where o.tinNumber = :tinNumber")
    int updateEmail(@Param("tinNumber") String tinNumber, @Param("email") String email);

    // allagh
    @Transactional
    @Modifying
    @Query("update Owner o set o.password = :password where o.tinNumber = :tinNumber")
    int updatePassword(@Param("tinNumber") String tinNumber, @Param("password") String password);

    @Modifying
    @Transactional
    @Query("update Owner o set o.email = ?2,o.address = ?3,o.phone  = ?4  where o.tinNumber = ?1")
    int updateOwnerByTinNumber(String tinNumber,  String email, String address,  String phone);

   @Transactional
//    @Modifying
//    @Query("delete Owner o where o.tinNumber = : tinNumber")
    void deleteByTinNumber(String tinNumber);


}
