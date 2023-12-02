package Salvation.Clinic.model.dto.request.TransactionRequest;


import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.UUID;

import static Salvation.Clinic.utills.MessageUtil.*;

@Data
public class PaymentRequest {

    @NotNull(message = INVALID_PRICE)
    @Min(value=10, message = INVALID_PRICE)
    private Double amountPaid;

    @NotNull(message = INVALID_DESCRIPTION)
    @NotEmpty(message = INVALID_DESCRIPTION)
    private String description;

    @NotNull(message = INVALID_STATUS)
    @NotEmpty(message = INVALID_STATUS)
    private String paymentMode;

    private UUID drugOrderId;






}
