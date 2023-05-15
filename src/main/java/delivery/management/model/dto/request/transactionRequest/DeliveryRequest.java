package delivery.management.model.dto.request.transactionRequest;

import delivery.management.model.dto.request.productsRequest.ProductItemsRequest;
import delivery.management.utills.MessageUtil;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static delivery.management.utills.MessageUtil.INVALID_NAME;

@Data
public class DeliveryRequest {

        private LocalDateTime deliveryDate;

        @NotNull(message = INVALID_NAME)
        @NotEmpty(message = INVALID_NAME)
        private String paymentMode;

        @NotNull(message = MessageUtil.INVALID_NAME)
        private UUID sendById;

        @NotNull(message = MessageUtil.INVALID_NAME)
        private UUID customerId;

        @NotNull(message = MessageUtil.INVALID_NAME)
        private UUID dispatchById;

        private List<ProductItemsRequest> items;


}

