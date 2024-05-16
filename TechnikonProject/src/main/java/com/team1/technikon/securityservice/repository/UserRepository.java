package com.team1.technikon.securityservice.repository;

import com.team1.technikon.model.Owner;
import com.team1.technikon.securityservice.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUsername(String username);

    @Query("select u from UserInfo u where u.role = :role ")
    List<Optional<UserInfo>> findUsersByRole(@Param("role")String role);

    @Query("select u from UserInfo u where u.owner IS NOT NULL ")
    List<Optional<UserInfo>> findUsersByOwnerNotNull();

    @Transactional
    @Modifying
    @Query("update UserInfo u set u = :user where u.owner.tinNumber = :tinNumber")
    int updateUserByOwnerTinNumber(@Param("tinNumber") String tinNumber, @Param("user") UserInfo userInfo);

    @Transactional
    @Modifying
    @Query("update UserInfo u set u = :user where u.id = :id")
    int updateUserByOwnerId(@Param("id") Long id, @Param("id") UserInfo userInfo);

    @Transactional
    @Modifying
    @Query("update UserInfo u set u.email = :email where u.id = :id")
    int updateUserEmail(@Param("id") Long id, @Param("email") String email);

    @Transactional
    @Modifying
    @Query("update UserInfo u set u.password = :password where u.id = :id")
    int updateUserPassword(@Param("id") Long id, @Param("password") String password);

    @Transactional
    @Modifying
    @Query("update UserInfo u  set u.owner = :owner where u.id = :id ")
    int updateUserWithOwner(@Param("id") Long id, @Param("owner") Owner owner);


//    @Transactional
//    @Modifying
//    @Query("update Owner o set o.email = :email where o.tinNumber = :tinNumber")
//    int updateEmail(@Param("tinNumber") String tinNumber, @Param("email") String email);
//
//    // allagh
//    @Transactional
//    @Modifying
//    @Query("update Owner o set o.password = :password where o.tinNumber = :tinNumber")
//    int updatePassword(@Param("tinNumber") String tinNumber, @Param("password") String password);
}