package Salvation.Clinic.model.dto.response.RoomResponse;


import Salvation.Clinic.model.dto.BaseDto;
import lombok.Data;

@Data
public class RoomCategoryResponse extends BaseDto {

    private String name;
    private String status;
    private String description;


}
