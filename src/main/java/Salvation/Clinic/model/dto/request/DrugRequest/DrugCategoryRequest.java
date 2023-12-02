package Salvation.Clinic.model.dto.request.DrugRequest;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.UUID;

import static Salvation.Clinic.utills.MessageUtil.*;

@Data
public class DrugCategoryRequest {

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    private String name;

    @NotNull(message = INVALID_DESCRIPTION)
    @NotEmpty(message = INVALID_DESCRIPTION)
    private String description;

    @NotNull(message = INVALID_STAFF_UUID)
//  @NotEmpty(message = INVALID_STAFF_UUID)
    private UUID createdById;

    @NotNull(message = INVALID_STAFF_UUID)
//  @NotEmpty(message = INVALID_STAFF_UUID)
    private UUID drugCategoryId;


}
