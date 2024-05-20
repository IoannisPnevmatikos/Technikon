package com.team1.technikon.repository;

import com.team1.technikon.dto.OwnerDto;
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


    @Query("select o from Owner o where o.isActive = true")
    List<Owner> findOwnersByIsActiveTrue();

    Optional<Owner> findByTinNumber(String tinNumber);

    @Query ("select o from Owner o where o.userInfo.email =:email")
    Optional<Owner> findOwnerByEmail(@Param("email") String email);

    @Query ("select o from Owner o where o.userInfo.firstName =:firstName")
    Optional<Owner> findOwnerByFirstName(@Param("firstName") String firstName);

    @Query ("select o from Owner o where o.userInfo.lastName =:lastName")
    Optional<Owner> findOwnerByLastName(@Param("lastName") String lastName);

   // @Transactional
    @Modifying
    @Query("update Owner o set o.address = :address where o.tinNumber = :tinNumber")
    int updateAddress( @Param("address") String address,@Param("tinNumber") String tinNumber);

    @Transactional
    @Modifying
    @Query("delete Owner o where o.tinNumber = :tinNumber")
    void deleteByTinNumber(@Param("tinNumber") String tinNumber);

  //  @Transactional
    @Modifying
    @Query("update Owner o SET o.phone = :phone where o.tinNumber = :tinNumber")
    int updateOwnerByPhone( @Param("phone") String phone,@Param("tinNumber") String tinNumber);

}
