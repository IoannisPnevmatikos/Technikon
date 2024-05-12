package com.team1.technikon.controller;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.dto.ResponseApi;
import com.team1.technikon.exception.OwnerFailToCreateException;
import com.team1.technikon.exception.OwnerNotFoundException;
import com.team1.technikon.model.Owner;
import com.team1.technikon.service.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/owner")
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping
    public ResponseApi<OwnerDto> createOwner(@RequestBody OwnerDto ownerDto) throws OwnerFailToCreateException {
        return new ResponseApi<>(0,"Owner successfully created",ownerService.createOwner(ownerDto));
    }

    @GetMapping
    public ResponseApi<List<OwnerDto>> allData() throws OwnerNotFoundException {
        return new ResponseApi<>(0,"Got all owners",ownerService.getAllData());
    }


    @GetMapping("/tinNumber/{tinNumber}")
    public ResponseApi<OwnerDto> findById(@PathVariable long tinNumber) throws OwnerNotFoundException {
        return new ResponseApi<>(0,"Owner successfully found by tinNumber",ownerService.getOwnerByTin(tinNumber));
    }

    @GetMapping("/email/{email}")
    public ResponseApi<OwnerDto> findByEmail(@PathVariable String email) throws OwnerNotFoundException  {

        return new ResponseApi<>(0,"Owner successfully found by email",ownerService.getOwnerByEmail(email));
    }

    @GetMapping("/username/{username}")
    public ResponseApi<OwnerDto> findByUsername(@PathVariable String username) throws OwnerNotFoundException {
        return new ResponseApi<>(0,"Owner successfully found By Username",ownerService.getOwnerByUsername(username));
    }

    @PutMapping("/address/{tinNumber}/{address}")
    public ResponseApi<Boolean> updateAddress(@PathVariable long tinNumber, @PathVariable String address) throws OwnerNotFoundException{
        return new ResponseApi<>(0,"Owner address successfully updated", ownerService.updateAddress(tinNumber, address));
    }

    @PutMapping("/email/{tinNumber}/{email}")
    public ResponseApi<Boolean> updateEmail(@PathVariable long tinNumber, @PathVariable String email)throws OwnerNotFoundException {
        return new ResponseApi<>(0,"Owner email successfully updated", ownerService.updateEmail(tinNumber, email));
    }

    //LOGIKA EINAI LATHOS IMPL
    @PutMapping("/password/{tinNumber}/{password}")
    public ResponseApi<Boolean> updatePassword(@PathVariable long tinNumber, @PathVariable String password) throws OwnerNotFoundException{
        return new ResponseApi<>(0,"Owner pw successfully updated",ownerService.updatePassword(tinNumber, password));
    }

    @DeleteMapping("/{tinNumber}")
    public ResponseApi<Boolean> deleteOwner(@PathVariable long tinNumber)throws OwnerNotFoundException {
        return new ResponseApi<>(0,"Owner successfully deleted",ownerService.deleteOwner(tinNumber));
    }

}
