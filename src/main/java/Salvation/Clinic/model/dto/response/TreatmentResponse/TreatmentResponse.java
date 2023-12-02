package Salvation.Clinic.model.dto.response.TreatmentResponse;


import Salvation.Clinic.model.dto.BaseDto;
import lombok.Data;

@Data
public class TreatmentResponse extends BaseDto {

    private String treatedBy;
    private String sickness;
    private String status;
    private Double cost;
    private String drugs;
    private String Description;


}
