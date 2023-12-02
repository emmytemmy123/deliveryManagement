package Salvation.Clinic.model.dto.response.TreatmentResponse;

import Salvation.Clinic.model.dto.BaseDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdmissionResponse extends BaseDto {

    private Integer roomNo;
    private Integer bedNo;
    private Integer noOfNights;
    private Double amount;
    private String status;
    private String description;
    private LocalDateTime admissionDate;
    private LocalDateTime dischargeDate;


}
