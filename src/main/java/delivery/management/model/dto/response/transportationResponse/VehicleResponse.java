package delivery.management.model.dto.response.transportationResponse;


import delivery.management.model.dto.BaseDto;
import lombok.Data;


@Data
public class VehicleResponse  extends BaseDto {

    private String brand;
    private String model;
    private String colour;
    private String plateNo;
    private String driver;



}
