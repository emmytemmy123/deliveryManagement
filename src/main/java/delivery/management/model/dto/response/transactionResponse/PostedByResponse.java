package delivery.management.model.dto.response.transactionResponse;

import lombok.Data;

@Data
public class PostedByResponse {

    private String name;
    private String email;
    private String phone;
    private String address;

}
