package Salvation.Clinic.model.dto.request.TransactionRequest;


import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

import static Salvation.Clinic.utills.MessageUtil.*;

@Data
public class OrderRequest {


//    @NotNull(message = INVALID_NUMBER)
//    @NotEmpty(message = INVALID_NUMBER)
    private String roomNo;

//    @NotNull(message = INVALID_NUMBER)
//    @Min(value=1, message = INVALID_NUMBER)
    private Integer quantity;

    private UUID OrderedById;

    private UUID userCategoryId;

    private UUID treatmentId;

    private List<DrugOrderItemsRequest> items;


}
