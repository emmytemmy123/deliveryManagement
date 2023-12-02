<<<<<<< HEAD:src/main/java/Salvation/Clinic/model/dto/response/UserResponse/UserCategoryResponse.java
package Salvation.Clinic.model.dto.response.UserResponse;


import Salvation.Clinic.model.dto.BaseDto;
=======
package delivery.management.model.dto.response.userResponse;

import delivery.management.model.dto.BaseDto;
>>>>>>> origin/master:src/main/java/delivery/management/model/dto/response/userResponse/UserCategoryResponse.java
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserCategoryResponse extends BaseDto {

    private String name;
    private String description;


}
