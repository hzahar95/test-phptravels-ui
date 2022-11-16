package com.phptravels.api;

import io.restassured.response.Response;

public class ApiClient extends BaseClient {
    public ApiClient(String targetDomain) {
        super(targetDomain);
    }

    public void register(RegistrationRequest registrationRequest) {
        Response response = requestMaker()
                .body(registrationRequest)
                .post("/api/login/signup");
    }

    public void login(LoginRequest loginRequest) {
        Response response = requestMaker()
                .body(loginRequest)
                .post("/api/login/check");
    }
}
