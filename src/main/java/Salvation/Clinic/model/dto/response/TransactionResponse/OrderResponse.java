package Salvation.Clinic.model.dto.response.TransactionResponse;

import Salvation.Clinic.model.dto.BaseDto;
import lombok.Data;

import java.util.List;

@Data
public class OrderResponse extends BaseDto {

    private String orderNo;
    private String orderBy;
    private Double amount;
    private Double amountDue;
    private Integer serialNo;
    private String roomNo;

    private List<OrderHelperResponse> drugOrderItemsList;


}
