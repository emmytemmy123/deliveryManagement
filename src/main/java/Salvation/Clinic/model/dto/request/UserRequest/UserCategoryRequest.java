package Salvation.Clinic.model.dto.request.UserRequest;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static Salvation.Clinic.utills.MessageUtil.INVALID_DESCRIPTION;
import static Salvation.Clinic.utills.MessageUtil.INVALID_NAME;

@Data
public class UserCategoryRequest {

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    private String name;

    @NotNull(message = INVALID_DESCRIPTION)
    @NotEmpty(message = INVALID_DESCRIPTION)
    private String description;


}
