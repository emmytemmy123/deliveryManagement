package Salvation.Clinic.model.dto.request.DrugRequest;


import lombok.Data;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

import static Salvation.Clinic.utills.MessageUtil.*;

@Data
public class DrugRequest {

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
    private String name;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    private String category;

//   @NotNull(message = INVALID_NAME)
//   @NotEmpty(message = INVALID_NAME)
    private String brand;

    @NotNull(message = INVALID_DATE_FORMAT)
//    @NotEmpty(message = INVALID_DATE_FORMAT)
    private Date expDate;

    @NotNull(message = INVALID_DESCRIPTION)
    @NotEmpty(message = INVALID_DESCRIPTION)
    private String description;

    @NotNull(message = INVALID_PRICE)
    @Min(value=10, message = INVALID_PRICE)
    private Double salesPrice;

    @NotNull(message = INVALID_QUANTITY)
    @Min(value = 1, message = INVALID_QUANTITY)
    private Integer quantity;

    @NotNull(message = INVALID_PRICE)
    @Min(value=10, message = INVALID_PRICE)
    private Double purchasedPrice;

    @NotNull(message = INVALID_STAFF_UUID)
    private UUID createdById;

    @NotNull(message = INVALID_STAFF_UUID)
    private UUID drugCategoryId;



}
