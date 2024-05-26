package com.team1.technikon.service.impl;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.dto.SignUpDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.mapper.Mapper;
import com.team1.technikon.model.Owner;
import com.team1.technikon.repository.OwnerRepository;
import com.team1.technikon.service.AdminOwnerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.team1.technikon.mapper.Mapper.mapToOwnerDto;
import static com.team1.technikon.validation.OwnerValidator.*;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class AdminOwnerServiceImpl extends OwnerServiceImpl implements AdminOwnerService {

    private final OwnerRepository ownerRepository;
    private final PasswordEncoder encoder;


    public String addUser(SignUpDto signUpDto) throws EntityFailToCreateException {
        Owner owner = new Owner();
        owner.setRole("USER");
        if (isValidSignUpDto(signUpDto)) {
            owner.setUsername(signUpDto.username());
            owner.setPassword(encoder.encode(signUpDto.password()));
            owner.setEmail(signUpDto.email());
            return ownerRepository.save(owner).toString();
        } else throw new EntityFailToCreateException("SignUp validation failed. Check user input again");
    }

    @Override
    public String addAdmin(SignUpDto signUpDto) {
        Owner owner = new Owner();
        owner.setRole("ADMIN");   //if(isValidSignUpDto(signUpDto)){
        owner.setUsername(signUpDto.username());
        owner.setPassword(encoder.encode(signUpDto.password()));
        owner.setEmail(signUpDto.email());
        return ownerRepository.save(owner).toString();    //}
    }


    @Override
    public List<OwnerDto> getAllOwners() throws EntityNotFoundException {
        try {
            return ownerRepository.findAll().stream().map(Mapper::mapToOwnerDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    public List<OwnerDto> getAllByRole(String role) throws EntityNotFoundException {

        if (role.equalsIgnoreCase("ADMIN"))
            return ownerRepository.findOwnersByRole("ADMIN").stream().map(Mapper::mapToOwnerDto).collect(Collectors.toList());
        else if (role.equalsIgnoreCase("USER"))
            return ownerRepository.findOwnersByRole("USER").stream().map(Mapper::mapToOwnerDto).collect(Collectors.toList());
        else throw new EntityNotFoundException("Entities with such role not found: " + role);
    }

    @Override
    public List<OwnerDto> getOwnersBetweenRegDate(LocalDate startDate, LocalDate endDate) throws EntityNotFoundException {
        try {
            return ownerRepository.findOwnersByRegistrationDate(startDate, endDate).stream().map(Mapper::mapToOwnerDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<OwnerDto> getOwnersActive(boolean isActive) throws EntityNotFoundException {
        try {
            return ownerRepository.findOwnersByIsActiveTrue(isActive).stream().map(Mapper::mapToOwnerDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public OwnerDto getOwnerById(Long id) throws EntityNotFoundException {
        return mapToOwnerDto(ownerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found")));
    }

    @Override
    public OwnerDto getOwnerByTinNumber(String tinNumber) throws EntityNotFoundException {
        try {
            if (isValidTinNumber(tinNumber)) {
                return mapToOwnerDto(ownerRepository.findByTinNumber(tinNumber).orElseThrow(() -> new EntityNotFoundException("Entity not found")));
            } else throw new EntityNotFoundException("Invalid tin Number. Check tinNumber again.");
        } catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public OwnerDto getOwnerByUsername(String username) throws EntityNotFoundException {
        return mapToOwnerDto(ownerRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Entity not found")));
    }

    @Override
    public OwnerDto getOwnerByEmail(String email) throws EntityNotFoundException {
        return mapToOwnerDto(ownerRepository.findOwnerByEmail(email).orElseThrow(() -> new EntityNotFoundException("Entity not found")));
    }

    @Override
    public OwnerDto getOwnerByFirstName(String firstName) throws EntityNotFoundException {
        return mapToOwnerDto(ownerRepository.findOwnerByFirstName(firstName).orElseThrow(() -> new EntityNotFoundException("Entity not found")));
    }

    @Override
    public OwnerDto getOwnerByLastName(String lastName) throws EntityNotFoundException {
        return mapToOwnerDto(ownerRepository.findOwnerByLastName(lastName).orElseThrow(() -> new EntityNotFoundException("Entity not found")));
    }

    @Transactional
    @Override
    public OwnerDto updateOwner(String username, OwnerDto ownerDto) throws EntityFailToCreateException, EntityNotFoundException {
        Owner owner = ownerRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Requested owner not found."));
       // if (ownerDto.tinNumber() != null) owner.setTinNumber(ownerDto.tinNumber());

        setUpdateFields(ownerDto, owner);
//
      //  if (ownerDto.email() != null && !ownerDto.email().equals(owner.getEmail())) owner.setEmail(ownerDto.email());
     //   if (ownerDto.phone() != null) owner.setPhone(ownerDto.phone());
        if( isValidOwner(mapToOwnerDto(owner))) {
            try {
                ownerRepository.save(owner);
                return mapToOwnerDto(owner);
            } catch (Exception e) {
                throw new EntityFailToCreateException("Valid Owner but update failed to execute.");
            }
        }
        else throw new EntityFailToCreateException("update failed to execute. Check Input again");
    }


    @Override
    public void deactivateOwnerByUsername(String username) throws EntityNotFoundException {
        Owner owner = ownerRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        owner.setActive(false);
        ownerRepository.save(owner);
    }

    @Override
    public boolean deleteOwnerByUsername(String username) throws EntityNotFoundException {
        try {
            ownerRepository.deleteByUsername(username);
            return ownerRepository.findByUsername(username).isEmpty();
        } catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }

    }

    static void setUpdateFields(OwnerDto ownerDto, Owner owner) {
        if (ownerDto.username() != null) owner.setUsername(owner.getUsername());
        if(ownerDto.password() != null) owner.setPassword(owner.getPassword());
        //WHen sign up tin is null, set the tin which comes from ownerdto
        if (ownerDto.tinNumber() != null && owner.getTinNumber() == null) owner.setTinNumber(ownerDto.tinNumber());
            //When owner tin already exists, dont change
        else owner.setTinNumber(owner.getTinNumber());
        if (ownerDto.address() != null) owner.setAddress(ownerDto.address());
        if (ownerDto.firstName() != null) owner.setFirstName(ownerDto.firstName());
        if (ownerDto.lastName() != null) owner.setLastName(ownerDto.lastName());
        if (ownerDto.email() != null) owner.setEmail(ownerDto.email());
        if (ownerDto.phone() != null) owner.setPhone(ownerDto.phone());
    }



}
