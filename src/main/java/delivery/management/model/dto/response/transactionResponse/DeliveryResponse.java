package delivery.management.model.dto.response.transactionResponse;


import delivery.management.model.dto.BaseDto;
import delivery.management.model.entity.products.ProductItems;
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
     private Integer serialNo;
     private String deliveryType;
     private Integer totalQuantity;
     private String postedBy;
     private String deliverBy;

     private List<ProductItems> productItemsItemList;

}
