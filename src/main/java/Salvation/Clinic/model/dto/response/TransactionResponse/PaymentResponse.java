package Salvation.Clinic.model.dto.response.TransactionResponse;

import Salvation.Clinic.model.dto.BaseDto;
import lombok.Data;

@Data
public class PaymentResponse extends BaseDto {

    private Double amountPaid;
    private String description;
    private String paymentMode;
    private String paymentStatus;
    private String postedBy;
    private String tranReference;
    private Double totalAmount;
    private Double balance;


}
