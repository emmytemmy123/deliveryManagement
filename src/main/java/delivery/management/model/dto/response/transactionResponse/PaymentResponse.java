package delivery.management.model.dto.response.transactionResponse;


import delivery.management.model.dto.BaseDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentResponse extends BaseDto {

     private String deliveryNo;
     private String paidBy;
     private String paidTo;
     private LocalDateTime paymentDate;
     private String paymentMode;
     private String status;
     private Double amountPaid;

//     private PaymentHelper delivery;

}
