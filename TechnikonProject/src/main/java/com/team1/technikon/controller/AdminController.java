package com.team1.technikon.controller;


import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.dto.SignUpDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.service.AdminOwnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@PreAuthorize("hasAuthority('ADMIN')")
@AllArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminOwnerService adminOwnerService;

    @PutMapping("/owner/create/{account}")
    public ResponseEntity<String> createOwner(@PathVariable String account, @RequestBody SignUpDto signUpDto) throws EntityFailToCreateException {
        if (account.equals("admin"))
            return ResponseEntity.ok(adminOwnerService.addAdmin(signUpDto));
        else
            return ResponseEntity.ok(adminOwnerService.addUser(signUpDto));
    }

    @GetMapping("/owner")
    public ResponseEntity<List<OwnerDto>> findAll() throws EntityNotFoundException {
        return ResponseEntity.ok(adminOwnerService.getAllOwners());
    }

    @GetMapping("/owner/{startDate}/to/{endDate}")
    public ResponseEntity<List<OwnerDto>> findAllByDate(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) throws EntityNotFoundException {
        return ResponseEntity.ok(adminOwnerService.getOwnersBetweenRegDate(startDate, endDate));
    }

    @GetMapping("/owner/active/{isActive}")
    public ResponseEntity<List<OwnerDto>> getActiveOwners(@PathVariable boolean isActive) throws EntityNotFoundException {
        return ResponseEntity.ok(adminOwnerService.getOwnersActive(isActive));
    }

    @GetMapping("/owner/role/{role}")
    public ResponseEntity<List<OwnerDto>> getOwnersByRole(@PathVariable("role") String role) throws EntityNotFoundException {
        return ResponseEntity.ok(adminOwnerService.getAllByRole(role));
    }


    @GetMapping("/owner/{id}")
    public ResponseEntity<OwnerDto> findById(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(adminOwnerService.getOwnerById(id));
    }


    @GetMapping("/owner/email/{email}")
    public ResponseEntity<OwnerDto> findByEmail(@PathVariable String email) throws EntityNotFoundException {
        return ResponseEntity.ok(adminOwnerService.getOwnerByEmail(email));
    }

    @GetMapping("/owner/name/{firstName}")
    public ResponseEntity<OwnerDto> findByFirstName(@PathVariable String firstName) throws EntityNotFoundException {
        return ResponseEntity.ok(adminOwnerService.getOwnerByFirstName(firstName));
    }

    @GetMapping("/owner/surname/{lastName}")
    public ResponseEntity<OwnerDto> findByLastName(@PathVariable String lastName) throws EntityNotFoundException {
        return ResponseEntity.ok(adminOwnerService.getOwnerByLastName(lastName));
    }

    @GetMapping("/owner/tinNum/{tinNumber}")
    public ResponseEntity<OwnerDto> findByTinNumber(@PathVariable String tinNumber) throws EntityNotFoundException {
        return ResponseEntity.ok(adminOwnerService.getOwnerByTinNumber(tinNumber));
    }

    @GetMapping("/owner/username/{username}")
    public ResponseEntity<OwnerDto> findByUsername(@PathVariable String username) throws EntityNotFoundException {
        return ResponseEntity.ok(adminOwnerService.getOwnerByUsername(username));
    }

    @PutMapping("/owner/{username}")
    public ResponseEntity<OwnerDto> updateOwner(@PathVariable("username") String username, @RequestBody OwnerDto ownerDto) throws EntityNotFoundException, EntityFailToCreateException {
        return ResponseEntity.ok(adminOwnerService.updateOwner(username, ownerDto));

    }

    @DeleteMapping("/owner/{username}")
    public ResponseEntity<String> deleteOwner(@PathVariable String username) throws EntityNotFoundException {
        adminOwnerService.deleteOwnerByUsername(username);
        return ResponseEntity.ok("Deleted owner with username " + username);
    }


    @PutMapping("/owner/deactivate/{username}")
    public ResponseEntity<String> deactivateOwnerByUsername (@PathVariable String username) throws EntityNotFoundException {
        adminOwnerService.deactivateOwnerByUsername(username);
        return ResponseEntity.ok("DeActivated owner with username " + username);
    }

}
