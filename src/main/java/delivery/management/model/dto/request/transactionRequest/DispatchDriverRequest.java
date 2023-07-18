package delivery.management.model.dto.request.transactionRequest;


import delivery.management.utills.MessageUtil;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class DispatchDriverRequest {

    private String driverEmail;

    private String deliveryNo;


}
