package delivery.management.model.dto.response.transactionResponse;


import delivery.management.model.dto.BaseDto;
import lombok.Data;

@Data
public class PaymentResponse extends BaseDto {

     private Double amount;
     private String description;
     private String paymentMode;
     private String paymentStatus;
     private String paidBy;
     private String tranReference;

//     private PaymentHelper delivery;

}
