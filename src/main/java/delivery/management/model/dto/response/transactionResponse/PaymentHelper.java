package delivery.management.model.dto.response.transactionResponse;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentHelper {

    private String name;
    private String email;
    private String phone;
    private String photo;
    private String gender;
    private String address;

}
