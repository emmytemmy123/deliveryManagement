package delivery.management.model.dto.request.userRequest;


import delivery.management.utills.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequest  {

    @NotNull(message = MessageUtil.INVALID_NAME)
    @NotEmpty(message = MessageUtil.INVALID_NAME)
    private String name;

    @NotNull(message = MessageUtil.INVALID_EMAIL)
    @NotEmpty(message = MessageUtil.INVALID_EMAIL)
    private String email;

    @NotNull(message = MessageUtil.INVALID_PHONE)
    @NotEmpty(message = MessageUtil.INVALID_PHONE)
    private String phone;

    @NotNull(message = MessageUtil.INVALID_GENDER)
    @NotEmpty(message = MessageUtil.INVALID_GENDER)
    private String gender;

    @NotNull(message = MessageUtil.INVALID_ADDRESS)
    @NotEmpty(message = MessageUtil.INVALID_ADDRESS)
    private String address;

    @NotNull(message = MessageUtil.INVALID_COUNTRY)
    @NotEmpty(message = MessageUtil.INVALID_COUNTRY)
    private String country;

    @NotNull(message = MessageUtil.INVALID_CITY)
    @NotEmpty(message = MessageUtil.INVALID_CITY)
    private String city;

    //   @NotNull(message = INVALID_FORMAT)
//   @NotEmpty(message = INVALID_FORMAT)
    private String photo;

    @NotNull(message = MessageUtil.INVALID_USERNAME)
    @NotEmpty(message = MessageUtil.INVALID_USERNAME)
    private String username;

    @NotNull(message = MessageUtil.INVALID_PASSWORD)
    @NotEmpty(message = MessageUtil.INVALID_PASSWORD)
    private String password;






}
