package delivery.management.model.dto.request.transactionRequest;

import delivery.management.utills.MessageUtil;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

import static delivery.management.utills.MessageUtil.INVALID_NAME;

@Data
public class PaymentRequest  {

       @NotNull(message = MessageUtil.INVALID_USERNAME)
       @NotEmpty(message = MessageUtil.INVALID_USERNAME)
       private String paymentMode;

       @NotNull(message = INVALID_NAME)
       @Min(value=50, message = INVALID_NAME)
       private Double amountPaid;

       @NotNull(message = INVALID_NAME)
       private UUID deliveryId;

       @NotNull(message = INVALID_NAME)
       private UUID dispatchId;


}
