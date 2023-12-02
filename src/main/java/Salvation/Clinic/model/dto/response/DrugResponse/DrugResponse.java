package Salvation.Clinic.model.dto.response.DrugResponse;


import Salvation.Clinic.model.dto.BaseDto;
import lombok.Data;

import java.util.Date;

@Data
public class DrugResponse extends BaseDto {

    private String category;
    private String brand;
    private String name;
    private String roomNo;
    private String bedNo;
    private String status;
    private String description;
    private Integer quantity;
    private Double salesPrice;
    private Double purchasePrice;
    private Date expDate;
    private String postedBy;


}
