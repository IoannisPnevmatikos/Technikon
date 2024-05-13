package com.team1.technikon.repository;

import com.team1.technikon.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    @Query("select o from Owner o where o.email = :email")
    Optional<Owner> getOwnerByEmail(@Param("email") String email);

    @Query("select o from Owner o where o.username = :username")
    Optional<Owner> getOwnerByUsername(@Param("username") String username);

    @Transactional
    @Modifying
    @Query("update Owner o set o.address = :address where o.tinNumber = :tinNumber")
    int updateAddress(@Param("tinNumber") long tinNumber, @Param("address") String address);

    @Transactional
    @Modifying
    @Query("update Owner o set o.email = :email where o.tinNumber = :tinNumber")
    int updateEmail(@Param("tinNumber") long tinNumber, @Param("email") String email);

    // allagh
    @Transactional
    @Modifying
    @Query("update Owner o set o.password = :password where o.tinNumber = :tinNumber")
    int updatePassword(@Param("tinNumber") long tinNumber, @Param("password") String password);


}
