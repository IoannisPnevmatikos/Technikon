package com.team1.technikon.securityservice.controller;

import com.team1.technikon.dto.SignUpDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.InvalidInputException;
import com.team1.technikon.securityservice.dto.AuthRequest;
import com.team1.technikon.securityservice.dto.ChangePwRequestDto;
import com.team1.technikon.securityservice.service.JwtService;
import com.team1.technikon.securityservice.service.UserInfoDetails;
import com.team1.technikon.service.AdminOwnerService;
import com.team1.technikon.service.OwnerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class UserController {
    private final OwnerService ownerService;
    private final AdminOwnerService adminOwnerService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/signup/user")
    public ResponseEntity<String> addUser(@RequestBody SignUpDto signUpDto) throws EntityFailToCreateException {
        String response = ownerService.addUser(signUpDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/signup/admin")
    public ResponseEntity<String> addAdmin(@RequestBody SignUpDto signUpDto) throws EntityFailToCreateException {
        try {
            String response = adminOwnerService.addAdmin(signUpDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            throw new EntityFailToCreateException(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
            String token = jwtService.generateToken(authRequest.getUsername(), userInfoDetails.getAuthorities().get(0).toString());
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
    public ResponseEntity<String> changePassword(@RequestBody ChangePwRequestDto changePwRequestDto) throws EntityNotFoundException, InvalidInputException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(changePwRequestDto.getUsername(), changePwRequestDto.getPassword()));
        logger.info("********************changePassword********************* {}", authentication);
        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(ownerService.updateOwnerPassword(changePwRequestDto));
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED! CHECK INPUTS AGAIN");
    }


}



