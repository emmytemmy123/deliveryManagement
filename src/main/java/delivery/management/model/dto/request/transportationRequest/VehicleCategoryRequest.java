package delivery.management.model.dto.request.transportationRequest;


import delivery.management.utills.MessageUtil;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class VehicleCategoryRequest {

    @NotNull(message = MessageUtil.INVALID_FIRSTNAME)
    @NotEmpty(message = MessageUtil.INVALID_FIRSTNAME)
    private String name;

    @NotNull(message = MessageUtil.INVALID_FIRSTNAME)
    @NotEmpty(message = MessageUtil.INVALID_FIRSTNAME)
    private String description;

    private UUID createdById;



}
