package delivery.management.model.dto.response.userResponse;

import delivery.management.model.dto.BaseDto;
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
