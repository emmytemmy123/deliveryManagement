package Salvation.Clinic.model.dto.response.DrugResponse;


import Salvation.Clinic.model.dto.BaseDto;
import lombok.Data;

import java.util.Date;

@Data
public class DrugPurchaseResponse extends BaseDto {

    private String name;
    private String category;
    private String description;
    private String companyName;
    private String brand;
    private Integer quantity;
    private Double price;
    private Date productPurchaseDate;


}
