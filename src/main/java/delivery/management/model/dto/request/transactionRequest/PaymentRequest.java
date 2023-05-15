package delivery.management.model.dto.request.transactionRequest;

import delivery.management.utills.MessageUtil;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class PaymentRequest  {


       @NotNull(message = MessageUtil.INVALID_NAME)
       @Min(value=50, message = MessageUtil.INVALID_NAME)
       private Double amount;

       @NotNull(message = MessageUtil.INVALID_NAME)
       private UUID deliveryId;


}
