package com.phptravels.models;

import com.phptravels.utils.FakerUtils;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String companyName;
    private String street;
    private String alternativeStreet;
    private String city;
    private String state;
    private String postcode;
    private String mobile;
    private String password;
    private String confirmPassword;

    public static User generateValidUser() {
        return User.builder()
                .firstName(FakerUtils.generateRandomFirstName())
                .lastName(FakerUtils.generateRandomLastName())
                .emailAddress(FakerUtils.generateValidEmail())
                .phoneNumber(FakerUtils.generateRandomPhoneNumber())
                .companyName(FakerUtils.generateRandomCompanyName())
                .street(FakerUtils.generateRandomStreet())
                .alternativeStreet(FakerUtils.generateRandomAlternativeStreet())
                .city(FakerUtils.generateRandomCity())
                .state(FakerUtils.generateRandomState())
                .postcode(FakerUtils.generateRandomPostcode())
                .mobile(FakerUtils.generateRandomMobile())
                .password(FakerUtils.generateStrongPassword())
                .confirmPassword(builder().password)
                .build();
    }

    public static User generateValidContact() {
        return User.builder()
                .firstName(FakerUtils.generateRandomFirstName())
                .lastName(FakerUtils.generateRandomLastName())
                .companyName(FakerUtils.generateRandomCompanyName())
                .emailAddress(FakerUtils.generateValidEmail())
                .phoneNumber(FakerUtils.generateRandomPhoneNumber())
                .street(FakerUtils.generateRandomStreet())
                .alternativeStreet(FakerUtils.generateRandomAlternativeStreet())
                .city(FakerUtils.generateRandomCity())
                .state(FakerUtils.generateRandomState())
                .postcode(FakerUtils.generateRandomPostcode())
                .build();
    }

}
