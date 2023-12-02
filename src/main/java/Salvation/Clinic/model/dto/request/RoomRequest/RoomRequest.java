package Salvation.Clinic.model.dto.request.RoomRequest;


import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.UUID;

import static Salvation.Clinic.utills.MessageUtil.*;

@Data
public class RoomRequest {

    @NotNull(message = INVALID_NUMBER)
    @NotEmpty(message=INVALID_NUMBER)
    private String roomNo;

    @NotNull(message = INVALID_PRICE)
    @Min(value = 1, message = INVALID_QUANTITY)
    private Integer noOfBed;

    @NotNull(message = INVALID_PRICE)
    @Min(value = 10, message = INVALID_PRICE)
    private Double cost;

    @NotNull(message = INVALID_STAFF_UUID)
    private UUID createdById;

    @NotNull(message = INVALID_STAFF_UUID)
    private UUID roomCategoryId;


}
