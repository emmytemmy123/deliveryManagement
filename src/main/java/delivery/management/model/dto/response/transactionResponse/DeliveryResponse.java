package delivery.management.model.dto.response.transactionResponse;

import delivery.management.model.dto.BaseDto;
import delivery.management.model.dto.response.productsResponse.ProductResponse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DeliveryResponse extends BaseDto {

     private String deliveryNo;
     private Double totalDeliveryAmount;
     private Double totalAmountDue;
     private String status;
     private String driverStatus;
     private String paymentStatus;
     private Integer serialNo;
     private String paymentMode;
     private Integer totalQuantity;
     private Integer totalWeight;
     private String postedBy;
     private String receiverName;
     private String receiverAddress;


//     private DispatchResponse dispatchDriver;
     private List<ProductResponse> productItemsList;

}
