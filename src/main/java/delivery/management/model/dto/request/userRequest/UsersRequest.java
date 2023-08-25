package delivery.management.model.dto.request.userRequest;

import delivery.management.utills.MessageUtil;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class UsersRequest {

        @NotNull(message = MessageUtil.INVALID_FIRSTNAME)
        @NotEmpty(message = MessageUtil.INVALID_FIRSTNAME)
        private String name;

        @NotNull(message = MessageUtil.INVALID_EMAIL)
        @NotEmpty(message = MessageUtil.INVALID_EMAIL)
        private String email;

//        @NotNull(message = MessageUtil.INVALID_GENDER)
//        @NotEmpty(message = MessageUtil.INVALID_GENDER)
        private String gender;

//        @NotNull(message = MessageUtil.INVALID_COUNTRY)
//        @NotEmpty(message = MessageUtil.INVALID_COUNTRY)
        private String country;

//        @NotNull(message = MessageUtil.INVALID_CITY)
//        @NotEmpty(message = MessageUtil.INVALID_CITY)
        private String city;

        @NotNull(message = MessageUtil.INVALID_ADDRESS)
        @NotEmpty(message = MessageUtil.INVALID_ADDRESS)
        private String address;

        @NotNull(message = MessageUtil.INVALID_PHONE)
        @NotEmpty(message = MessageUtil.INVALID_PHONE)
        private String phone;

//        @NotNull(message = MessageUtil.INVALID_PASSWORD)
//        @NotEmpty(message = MessageUtil.INVALID_PASSWORD)
        private String driverLicense;

        @NotNull(message = MessageUtil.INVALID_USERNAME)
        @NotEmpty(message = MessageUtil.INVALID_USERNAME)
        private String username;

        @NotNull(message = MessageUtil.INVALID_PASSWORD)
        @NotEmpty(message = MessageUtil.INVALID_PASSWORD)
        private String password;

        @NotNull(message = MessageUtil.INVALID_PASSWORD)
        @NotEmpty(message = MessageUtil.INVALID_PASSWORD)
        private String userCategory;




}
