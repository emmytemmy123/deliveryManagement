package Salvation.Clinic.model.dto.request.UserRequest;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static Salvation.Clinic.utills.MessageUtil.*;

@Data
public class UsersRequest {

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    private String name;

    @NotNull(message = INVALID_EMAIL)
    @NotEmpty(message = INVALID_EMAIL)
    private String email;

    @NotNull(message = INVALID_GENDER)
    @NotEmpty(message = INVALID_GENDER)
    private String gender;

    @NotNull(message = INVALID_COUNTRY)
    @NotEmpty(message = INVALID_COUNTRY)
    private String country;

    @NotNull(message = INVALID_CITY)
    @NotEmpty(message = INVALID_CITY)
    private String city;

    @NotNull(message = INVALID_ADDRESS)
    @NotEmpty(message = INVALID_ADDRESS)
    private String address;

    @NotNull(message = INVALID_PHONE)
    @NotEmpty(message = INVALID_PHONE)
    private String phone;

    @NotNull(message = INVALID_DESIGNATION)
    @NotEmpty(message = INVALID_DESIGNATION)
    private String designation;

    @NotNull(message = INVALID_USERNAME)
    @NotEmpty(message = INVALID_USERNAME)
    private String username;

    @NotNull(message = INVALID_PASSWORD)
    @NotEmpty(message = INVALID_PASSWORD)
    private String password;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    private String userCategory;



}
