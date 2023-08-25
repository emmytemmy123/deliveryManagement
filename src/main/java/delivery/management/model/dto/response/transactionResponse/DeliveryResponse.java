package delivery.management.model.dto.response.transactionResponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import delivery.management.model.dto.BaseDto;
import delivery.management.model.dto.response.productsResponse.ProductResponse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
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
     @JsonFormat(pattern = "yyyy/MM/dd")
     private Date dispatchDate;


//     private DispatchResponse dispatchDriver;
     private List<ProductResponse> productItemsList;

}
