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
     private String deliveryStatus;
     private LocalDateTime deliveryDate;
     private String customerAddress;
     private Integer serialNo;
     private String paymentMode;
     private Integer totalQuantity;
     private Integer totalWeight;

     private PostedByResponse sender;
     private DeliverByResponse driver;
     private DispathToResponse customer;
     private List<ProductResponse> productItemsList;

}
