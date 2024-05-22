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


    @Query("select o from Owner o where o.isActive = true")
    List<Owner> findOwnersByIsActiveTrue();

    Optional<Owner> findByTinNumber(String tinNumber);

    @Query("select o from Owner o where o.email =:email")
    Optional<Owner> findOwnerByEmail(@Param("email") String email);

    @Query("select o from Owner o where o.firstName =:firstName")
    Optional<Owner> findOwnerByFirstName(@Param("firstName") String firstName);

    @Query("select o from Owner o where o.lastName =:lastName")
    Optional<Owner> findOwnerByLastName(@Param("lastName") String lastName);

    Optional<Owner> findByUsername(String username);

//    @Query("select o from Owner o where o.role = :role ")
//    List<Optional<Owner>> findUsersByRole(@Param("role") String role);

    @Transactional
    @Modifying
    @Query("update Owner o set o.password = :password where o.username = :username")
    int updateOwnerPassword(@Param("username") String username, @Param("password") String password);

    @Transactional
    @Modifying
    @Query("delete Owner o where o.tinNumber = :tinNumber")
    void deleteByTinNumber(@Param("tinNumber") String tinNumber);

}
