package com.team1.technikon.securityservice.controller;

import com.team1.technikon.dto.SignUpDto;
import com.team1.technikon.exception.OwnerFailToCreateException;
import com.team1.technikon.exception.OwnerNotFoundException;
import com.team1.technikon.securityservice.dto.AuthRequest;
import com.team1.technikon.securityservice.service.JwtService;
import com.team1.technikon.service.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class UserController {
    private final OwnerService ownerService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup/user")
    public ResponseEntity<String> addUser(@RequestBody SignUpDto signUpDto) throws OwnerFailToCreateException {
        String response = ownerService.addUser(signUpDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/signup/admin")
    public ResponseEntity<String> addAdmin(@RequestBody SignUpDto signUpDto) throws OwnerFailToCreateException {
        String response = ownerService.addAdmin(signUpDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(token);
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("/users")
//    public ResponseEntity<List<Owner>> getAdmin() {
//        return ResponseEntity.ok(ownerService.findOwnersByRole("ADMIN").stream().map(Optional::get).collect(Collectors.toList()));
//    }

    //change user info updates
    //  @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/changePassword")
    public ResponseEntity<Boolean> changePassword(@RequestBody AuthRequest authRequest, String newPw) {
        //ISWS NA TSEKAREI MONO TO TOKEN AN EINAI VALIDATED XWRIS NA KSANAKANEI AUTHENTICATION
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(ownerService.updateOwnerPassword(authRequest.getUsername(), newPw));
        } else return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
    }


//    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("/{userId}/addOwner")
//    public ResponseEntity<Boolean> saveOwner(@PathVariable(name = "userId") Long userId, @RequestBody OwnerDto ownerDto ) throws OwnerNotFoundException {
//        return ResponseEntity.ok(service.saveUserOwner(userId,ownerDto));
//    }

}



