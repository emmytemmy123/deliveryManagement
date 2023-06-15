package delivery.management.model.dto.request.userRequest;


import delivery.management.utills.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class changeUsersPasswordRequest {

    @NotNull(message = MessageUtil.INVALID_PASSWORD)
    @NotEmpty(message = MessageUtil.INVALID_PASSWORD)
    private String oldPassword;

    @NotNull(message = MessageUtil.INVALID_PASSWORD)
    @NotEmpty(message = MessageUtil.INVALID_PASSWORD)
    private String newPassword;

}
