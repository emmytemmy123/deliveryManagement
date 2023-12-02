package Salvation.Clinic.model.dto.response.TransactionResponse;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderHelperResponse {

    private String itemName;
    private Integer quantity;
    private Double amount;
    private String description;
    private String status;
    private String orderBy;
    private String administerTo;
    private String orderNo;
    private Double amountDue;
    private String roomNo;

}
