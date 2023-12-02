package Salvation.Clinic.model.dto.request.TreatmentRequest;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

import static Salvation.Clinic.utills.MessageUtil.*;

@Data
public class AdmissionRequest {

//    @NotNull(message = INVALID_QUANTITY)
//    @Min(value = 1, message = INVALID_QUANTITY)
    private Integer noOfNights;

////    @NotNull(message = INVALID_QUANTITY)
////    @Min(value = 1, message = INVALID_QUANTITY)
//    private Double amount;

    @NotNull(message = INVALID_DESCRIPTION)
//    @NotEmpty(message = INVALID_DESCRIPTION)
    private String description;

    private UUID createdById;

    private UUID treatmentId;

    private UUID roomId;

}
