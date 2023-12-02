package Salvation.Clinic.model.dto.request.TransactionRequest;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static Salvation.Clinic.utills.MessageUtil.*;

@Data
public class DrugOrderItemsRequest {

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    private String itemName;

    @NotNull(message = INVALID_QUANTITY)
    @Min(value=1, message = INVALID_QUANTITY)
    private Integer quantity;

    @NotNull(message = INVALID_DESCRIPTION)
    @NotEmpty(message = INVALID_DESCRIPTION)
    private String description;

    private UUID drugId;

    private List<DrugOrderItemsRequest> items;


}
