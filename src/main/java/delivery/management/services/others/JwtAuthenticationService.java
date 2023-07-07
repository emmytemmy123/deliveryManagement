package delivery.management.services.others;

import delivery.management.dto.ApiResponse;
import delivery.management.model.dto.request.othersRequest.AuthRequest;
import delivery.management.model.dto.response.othersResponse.AuthResponse;
import delivery.management.model.dto.response.userResponse.UsersResponse;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.UUID;

public interface JwtAuthenticationService {


    String giveAccessToUser(UUID uuid, String userRole, Principal principal);

    ResponseEntity<?> authenticateUsernameAndPassword(AuthRequest authRequest);



}
