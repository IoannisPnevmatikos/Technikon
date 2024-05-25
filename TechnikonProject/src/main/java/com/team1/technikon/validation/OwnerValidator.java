package com.team1.technikon.validation;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.dto.SignUpDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.regex.Pattern;

@Slf4j
public class OwnerValidator {


    private static final Logger logger = LoggerFactory.getLogger(OwnerValidator.class);

    public static boolean isValidOwner(OwnerDto ownerDto) {
        logger.info("isValidOwner");
        return (isValidEmail(ownerDto.email()) &&
                isValidUsername(ownerDto.username()) &&
                isValidName(ownerDto.firstName(), ownerDto.lastName()) &&
                isValidTinNumber(ownerDto.tinNumber())&&
            isValidPhone(ownerDto.phone()));

    }

    public static boolean isValidTinNumber(String tinNumber) {
        String regexPattern = "^[0-9]{9}$";
        return Pattern.compile(regexPattern).matcher(tinNumber).matches();
    }


    public static boolean isValidPhone(String phone) {
        return phone.length() == 10;
    }

    public static boolean isValidEmail(String email) {
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return Pattern.compile(regexPattern).matcher(email).matches();
    }

    public static boolean isValidUsername(String username) {
        String regexPattern = "^[A-Za-z]\\w{5,29}$";
        return Pattern.compile(regexPattern).matcher(username).matches();
    }

    public static boolean isValidName(String firstName, String lastName) {
        String regexPattern = "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$";
        return Pattern.compile(regexPattern).matcher(firstName).matches() && Pattern.compile(regexPattern).matcher(lastName).matches();
    }

    public static boolean isValidSignUpDto(SignUpDto signUpDto) {
        logger.info("isValidSignUpDto: {}", signUpDto);
        return isValidUsername(signUpDto.username()) && isValidEmail(signUpDto.email());
    }
}

