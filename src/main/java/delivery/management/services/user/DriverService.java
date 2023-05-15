package delivery.management.services.user;


import delivery.management.model.dto.request.userRequest.*;
import delivery.management.model.dto.response.othersResponse.ApiResponse;
import delivery.management.model.dto.response.userResponse.CustomerResponse;
import delivery.management.model.dto.response.userResponse.DriverResponse;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface DriverService {

    ApiResponse<List<DriverResponse>> getListOfDriver(int page, int size);

    ApiResponse<String> addDriver(DriverRequest request) throws IOException;

    ApiResponse<DriverResponse> getDriverById(UUID driverId);

    ApiResponse<String> updateDriver( UUID driverId, DriverRequest request);

    ApiResponse<String> deleteDriver(UUID driverId);

    ApiResponse<String> resetDriverPassword(String email, changeDriverPasswordRequest request);

    ApiResponse<String> forgotDriverPassword(String email) throws MessagingException;

    ApiResponse<String> loginDriver(String email, loginDriverRequest request);

}
