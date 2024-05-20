package com.team1.technikon.securityservice.service;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.exception.OwnerNotFoundException;
import com.team1.technikon.mapper.TechnikonMapper;
import com.team1.technikon.model.Owner;
import com.team1.technikon.securityservice.dto.UserInfoDto;
import com.team1.technikon.securityservice.model.UserInfo;
import com.team1.technikon.securityservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    //    private final UserInfoMapper userInfoMapper;
//    private final TechnikonMapper technikonMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userDetail = repository.findByUsername(username);
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public Optional<UserInfo> findUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    public List<Optional<UserInfo>> findUsersByRole() {
        String role = "USER";
        return repository.findUsersByRole(role);
    }

    public List<Optional<UserInfo>> findUsersOwners() {
        return repository.findUsersByOwnerNotNull();
    }
//
//    // Update Every field of userInfoDto WITH ID
//    public boolean updateUserByOwnerId(Long id, UserInfoDto userInfoDto) {
//        User user = userInfoMapper.toUserInfo(userInfoDto);
//        return repository.updateUserByOwnerId(id, user) == 1;
//    }
//    // Update Every field of userInfoDto WITH TINNUMBER
//    public boolean updateUserOwnerByTinNumber(String tinNumber, UserInfoDto userInfoDto) {
//        User user = userInfoMapper.toUserInfo(userInfoDto);
//        return repository.updateUserByOwnerTinNumber(tinNumber, user) == 1;
//    }


    public boolean updateUserPassword(Long id, String password) {
        return repository.updateUserPassword(id, password) == 1;
    }

    //Initialize user afterwards
//    public boolean saveUserOwner(Long id, OwnerDto ownerDto) throws OwnerNotFoundException {
//        try {
//            Owner owner = new Owner();
//            ow
//            if (repository.findById(id).get().getOwner() == null)
//                return repository.updateUserWithOwner(id, owner) == 1;
//            else {
//                throw new OwnerNotFoundException("Owner already exists. Update owner from owner");
//            }
//        } catch (Exception e) {
//            throw new OwnerNotFoundException(e.getMessage());
//        }
//
//    }

    public boolean updateUserEmail(Long id, String email) throws OwnerNotFoundException {
        try {
            return repository.updateUserEmail(id, email) == 1;
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());
        }
    }

    public String addUser(UserInfoDto userInfoDto) {
        UserInfo userInfo = new UserInfo(
                userInfoDto.firstName(),
                userInfoDto.lastName(),
                userInfoDto.username(),
                userInfoDto.email(),
                encoder.encode(userInfoDto.password()),
                "USER",
                null
        );
//        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
//        userInfo.setRole("USER");
        repository.save(userInfo);
        return "User Added Successfully";
    }

    public String addAdmin(UserInfoDto userInfoDto) {

        if(isValidUserInfo(userInfoDto)) {
            UserInfo userInfo = new UserInfo(
                    userInfoDto.firstName(),
                    userInfoDto.lastName(),
                    userInfoDto.username(),
                    userInfoDto.email(),
                    encoder.encode(userInfoDto.password()),
                    "ADMIN",
                    null
            );
            userInfo.setPassword(encoder.encode(userInfo.getPassword()));
            userInfo.setRole("ADMIN");
            repository.save(userInfo);
            return "User Added Successfully";
        }
        return "Invalid User fields or duplicate";
    }

    private boolean isValidUserInfo(UserInfoDto userInfoDto) {
        return ( isValidEmail(userInfoDto.email()) &&
                isValidUsername(userInfoDto.username())&&
                isValidName(userInfoDto.firstName(), userInfoDto.lastName()));
    }
    private boolean isValidEmail(String email) {
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return Pattern.compile(regexPattern).matcher(email).matches();
    }
    private boolean isValidUsername(String username) {
        String regexPattern = "^[A-Za-z]\\w{5,29}$";
        return Pattern.compile(regexPattern).matcher(username).matches();
    }

    private boolean isValidName(String firstName, String lastName) {
        String regexPattern = "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$";
        return Pattern.compile(regexPattern).matcher(firstName).matches() && Pattern.compile(regexPattern).matcher(lastName).matches();
    }

}