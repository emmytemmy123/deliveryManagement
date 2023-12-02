package Salvation.Clinic.model.dto.request.UserRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static Salvation.Clinic.utills.MessageUtil.INVALID_PASSWORD;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class changeUserPasswordRequest {

    @NotNull(message = INVALID_PASSWORD)
    @NotEmpty(message = INVALID_PASSWORD)
    private String oldPassword;

    @NotNull(message = INVALID_PASSWORD)
    @NotEmpty(message = INVALID_PASSWORD)
    private String newPassword;

}
