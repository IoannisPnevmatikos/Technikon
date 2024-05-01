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

    @PostMapping("/create")
    public Owner ownerCreate(@RequestBody OwnerDto ownerDto) {
        return ownerService.createOwner(ownerDto);
    }

    @GetMapping("/allData")
    public List<Owner> allData() {
        return ownerService.getAllData();
    }


    @GetMapping("tin/{id}")
    public Owner findById(@PathVariable long tinNumber) {
        return ownerService.getOwnerByTin(tinNumber);
    }

    @GetMapping("email/{email}")
    public Owner findByEmail(@PathVariable String email) {
        return ownerService.getOwnerByEmail(email);
    }

    @GetMapping("username/{username}")
    public Owner findByUsername(@PathVariable String username) {
        return ownerService.getOwnerByUsername(username);
    }

    @PutMapping("/updateAddress")
    public boolean updateAddress(@RequestParam(value = "tinNumber") long tinNumber, @RequestParam(value = "address") String address) {
        return ownerService.updateAddress(tinNumber, address);
    }

    @PutMapping("/updateEmail")
    public boolean updateEmail(@RequestParam(value = "tinNumber") long tinNumber, @RequestParam(value = "email") String email) {
        return ownerService.updateEmail(tinNumber, email);
    }

    //LOGIKA EINAI LATHOS IMPL
    @PutMapping("/updatePassword")
    public boolean updatePassword(@RequestParam(value = "tinNumber") long tinNumber, @RequestParam(value = "password") String password) {
        return ownerService.updatePassword(tinNumber, password);
    }

    @DeleteMapping("/delete/{tinNumber}")
    public boolean deleteOwner(@PathVariable long tinNumber) {
        return ownerService.deleteOwner(tinNumber);
    }

}
