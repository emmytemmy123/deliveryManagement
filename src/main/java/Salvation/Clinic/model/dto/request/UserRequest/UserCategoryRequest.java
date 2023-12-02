<<<<<<< HEAD:src/main/java/Salvation/Clinic/model/dto/request/UserRequest/UserCategoryRequest.java
package Salvation.Clinic.model.dto.request.UserRequest;
=======
package delivery.management.model.dto.request.userRequest;
>>>>>>> origin/master:src/main/java/delivery/management/model/dto/request/userRequest/UserCategoryRequest.java


import lombok.Data;

<<<<<<< HEAD:src/main/java/Salvation/Clinic/model/dto/request/UserRequest/UserCategoryRequest.java
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
=======
@Data
public class UserCategoryRequest {

    private String name;
>>>>>>> origin/master:src/main/java/delivery/management/model/dto/request/userRequest/UserCategoryRequest.java
    private String description;


}
