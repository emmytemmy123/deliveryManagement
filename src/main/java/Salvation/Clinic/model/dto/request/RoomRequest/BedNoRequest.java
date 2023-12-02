package Salvation.Clinic.model.dto.request.RoomRequest;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static Salvation.Clinic.utills.MessageUtil.INVALID_NUMBER;

@Data
public class BedNoRequest {

    @NotNull(message = INVALID_NUMBER)
    @NotEmpty(message=INVALID_NUMBER)
    private String name;


    //    @NotNull(message = INVALID_NUMBER)
//    @NotEmpty(message=INVALID_NUMBER)
    private String description;



}
