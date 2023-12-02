package Salvation.Clinic.model.dto.request.TreatmentRequest;


import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.UUID;

import static Salvation.Clinic.utills.MessageUtil.*;

@Data
public class TreatmentRequest {

//    @NotNull(message = INVALID_NAME)
//    @NotEmpty(message = INVALID_NAME)
    private String treatedBy;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    private String sickness;

    @NotNull(message = INVALID_STATUS)
//    @NotEmpty(message = INVALID_STATUS)
    private String status;

    @NotNull(message = INVALID_PRICE)
    @Min(value=10, message = INVALID_PRICE)
    private Double cost;

//    @NotNull(message = INVALID_NAME)
//    @NotEmpty(message = INVALID_NAME)
    private String drugs;

    @NotNull(message = INVALID_DESCRIPTION)
//    @NotEmpty(message = INVALID_DESCRIPTION)
    private String Description;

    private UUID patientId;

    private UUID treatedById;

    private UUID drugId;



}
