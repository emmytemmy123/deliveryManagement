package Salvation.Clinic.model.dto.response.TransactionResponse;

import Salvation.Clinic.model.dto.BaseDto;
import lombok.Data;

@Data
public class DrugOrderItemsResponse extends BaseDto {

    private String orderNo;
    private String orderBy;
    private Double amount;
    private Double amountDue;
    private String drugStatus;
    private Integer serialNo;
    private String roomStatus;
    private String roomNo;


}
