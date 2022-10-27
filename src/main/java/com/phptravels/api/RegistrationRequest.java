package com.phptravels.api;

import lombok.Data;

@Data
public class RegistrationRequest {
    private final String first_name;
    private final String last_name;
    private final String email;
    private final String password;
    private final String phone;
    private final String status;
    private final String type;
}
