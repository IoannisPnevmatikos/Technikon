//package com.team1.technikon.securityservice.service;
//
//import com.team1.technikon.dto.OwnerDto;
//import com.team1.technikon.exception.OwnerNotFoundException;
//import com.team1.technikon.model.Owner;
//import com.team1.technikon.repository.OwnerRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.regex.Pattern;
//
//import com.team1.technikon.mapper.MapperTemp;
//
//@Service
//@AllArgsConstructor
//public class UserService implements UserDetailsService {
//    private final OwnerRepository repository;
//    private final PasswordEncoder encoder;
//
//
//    public Optional<Owner> findUserByUsername(String username) {
//        return repository.findByUsername(username);
//    }
//
//    public List<Optional<Owner>> findUsersByRole(String role) {
//        return repository.findUsersByRole(role);
//    }
//
//
//    public boolean updateUserPassword(Long id, String password) {
//        return repository.updateOwnerPassword(id, password) == 1;
//    }
//
//    public boolean updateUserEmail(Long id, String email) throws OwnerNotFoundException {
//        try {
//            return repository.updateOwnerEmail(id, email) == 1;
//        } catch (Exception e) {
//            throw new OwnerNotFoundException(e.getMessage());
//        }
//    }
//
////    public String addUser(OwnerDto ownerDto) {
////        Owner owner = MapperTemp.mapToOwner(ownerDto);
////        repository.save(owner);
////        return "Owner Added Successfully";
////    }
//
////    public String addAdmin(OwnerDto ownerDto) {
////
////        if(isValidOwnerInfo(ownerDto)) {
////            Owner owner = MapperTemp.mapToOwner(ownerDto);
////            repository.save(owner);
////            return "User Added Successfully";
////        }
////        return "Invalid User fields or duplicate";
////    }
//
//}