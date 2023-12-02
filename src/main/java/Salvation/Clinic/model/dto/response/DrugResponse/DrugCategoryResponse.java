package Salvation.Clinic.model.dto.response.DrugResponse;

import Salvation.Clinic.model.dto.BaseDto;
import lombok.Data;


@Data
public class DrugCategoryResponse extends BaseDto {

    private String name;
    private String description;

}
