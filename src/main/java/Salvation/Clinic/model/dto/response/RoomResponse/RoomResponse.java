package Salvation.Clinic.model.dto.response.RoomResponse;

import Salvation.Clinic.model.dto.BaseDto;
import lombok.Data;

@Data
public class RoomResponse extends BaseDto {

    private String roomNo;
    private Integer bedNo;
    private Double cost;
    private String status;


}
