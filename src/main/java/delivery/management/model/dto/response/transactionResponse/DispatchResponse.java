package delivery.management.model.dto.response.transactionResponse;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DispatchResponse {

    private String deliveryNo;
    private String sender;
    private String dispatchName;
    private String receiverAddress;
    private String receiverName;
    private Double totalAmount;
    private LocalDateTime dispatchDate;
    private String email;


}
