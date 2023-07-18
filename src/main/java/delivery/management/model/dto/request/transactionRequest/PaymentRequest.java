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

       @NotNull(message = MessageUtil.INVALID_DELIVERY_NO)
       private String dispatchDeliveryNo;

       @NotNull(message = MessageUtil.INVALID_DELIVERY_NO)
       private String deliveryNo;




}
