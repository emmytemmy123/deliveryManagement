package Salvation.Clinic.model.dto.response.UserResponse;


import Salvation.Clinic.model.dto.BaseDto;
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
