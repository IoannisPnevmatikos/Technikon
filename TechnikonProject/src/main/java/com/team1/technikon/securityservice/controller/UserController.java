package com.team1.technikon.securityservice.controller;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.exception.OwnerNotFoundException;
import com.team1.technikon.securityservice.dto.AuthRequest;
import com.team1.technikon.securityservice.dto.UserInfoDto;
import com.team1.technikon.securityservice.mapper.UserInfoMapper;
import com.team1.technikon.securityservice.model.UserInfo;
import com.team1.technikon.securityservice.service.JwtService;
import com.team1.technikon.securityservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class UserController {
    private final UserService service;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
//    private final UserInfoMapper userInfoMapper;

    @PostMapping("/signup/user")
    public ResponseEntity<String> addNewUser(@RequestBody UserInfoDto userInfoDto) {
        String response = service.addUser(userInfoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/signup/admin")
    public ResponseEntity<String> addNewAdmin(@RequestBody UserInfoDto userInfoDto) {
        String response = service.addAdmin(userInfoDto);
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/owners")
    public ResponseEntity<List<UserInfo>> getOwners() {
        return ResponseEntity.ok(service.findUsersOwners().stream().map(Optional::get).collect(Collectors.toList()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<UserInfo>> getUsers() {
        return ResponseEntity.ok(service.findUsersByRole().stream().map(Optional::get).collect(Collectors.toList()));
    }

    //change user info updates
  //  @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/changePassword")
    public ResponseEntity<Boolean> changePassword(@RequestBody AuthRequest authRequest, String newPw) {
        //ISWS NA TSEKAREI MONO TO TOKEN AN EINAI VALIDATED XWRIS NA KSANAKANEI AUTHENTICATION
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(service.updateUserPassword(service.findUserByUsername(authRequest.getUsername()).get().getId(), newPw));
        } else return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
    }

//    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/{userId}/changeEmail")
    public ResponseEntity<Boolean> changeEmail(@PathVariable(name = "userId") Long userId, @RequestBody String newEmail) throws OwnerNotFoundException {
      return ResponseEntity.ok(service.updateUserEmail(userId,newEmail));
    }


//    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("/{userId}/addOwner")
//    public ResponseEntity<Boolean> saveOwner(@PathVariable(name = "userId") Long userId, @RequestBody OwnerDto ownerDto ) throws OwnerNotFoundException {
//        return ResponseEntity.ok(service.saveUserOwner(userId,ownerDto));
//    }

}



