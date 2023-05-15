package delivery.management.model.dto.request.transportationRequest;


import delivery.management.utills.MessageUtil;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

import static delivery.management.utills.MessageUtil.INVALID_NAME;

@Data
public class VehicleRequest {

    @NotNull(message = MessageUtil.INVALID_FIRSTNAME)
    @NotEmpty(message = MessageUtil.INVALID_FIRSTNAME)
    private String brand;

    @NotNull(message = MessageUtil.INVALID_FIRSTNAME)
    @NotEmpty(message = MessageUtil.INVALID_FIRSTNAME)
    private String model;

    @NotNull(message = MessageUtil.INVALID_FIRSTNAME)
    @NotEmpty(message = MessageUtil.INVALID_FIRSTNAME)
    private String colour;

    @NotNull(message = MessageUtil.INVALID_FIRSTNAME)
    @NotEmpty(message = MessageUtil.INVALID_FIRSTNAME)
    private String plateNo;

    @NotNull(message = INVALID_NAME)
    private UUID driverId;

    @NotNull(message = INVALID_NAME)
    private UUID vehicleCategoryId;



}
