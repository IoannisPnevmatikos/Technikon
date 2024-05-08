package com.team1.technikon.controller;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.model.Owner;
import com.team1.technikon.service.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/owner")
public class OwnerController {


    private final OwnerService ownerService;

    @PostMapping
    public Owner ownerCreate(@RequestBody OwnerDto ownerDto) {
        return ownerService.createOwner(ownerDto);
    }

    @GetMapping
    public List<Owner> allData() {
        return ownerService.getAllData();
    }


    @GetMapping("/tinNumber/{tinNumber}")
    public Owner findById(@PathVariable long tinNumber) {
        return ownerService.getOwnerByTin(tinNumber);
    }

    @GetMapping("/email/{email}")
    public Owner findByEmail(@PathVariable String email) {
        return ownerService.getOwnerByEmail(email);
    }

    @GetMapping("/username/{username}")
    public Owner findByUsername(@PathVariable String username) {
        return ownerService.getOwnerByUsername(username);
    }

    @PutMapping("/address/{tinNumber}/{address}")
    public boolean updateAddress(@PathVariable long tinNumber, @PathVariable String address) {
        return ownerService.updateAddress(tinNumber, address);
    }

    @PutMapping("/email/{tinNumber}/{email}")
    public boolean updateEmail(@PathVariable long tinNumber, @PathVariable String email) {
        return ownerService.updateEmail(tinNumber, email);
    }

    //LOGIKA EINAI LATHOS IMPL
    @PutMapping("/password/{tinNumber}/{password}")
    public boolean updatePassword(@PathVariable long tinNumber, @PathVariable String password) {
        return ownerService.updatePassword(tinNumber, password);
    }

    @DeleteMapping("/{tinNumber}")
    public boolean deleteOwner(@PathVariable long tinNumber) {
        return ownerService.deleteOwner(tinNumber);
    }

}
