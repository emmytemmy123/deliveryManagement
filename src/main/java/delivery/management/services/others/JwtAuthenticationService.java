package delivery.management.services.others;

import delivery.management.model.dto.request.othersRequest.AuthRequest;

import java.security.Principal;
import java.util.UUID;

public interface JwtAuthenticationService {

    String authenticateUsernameAndPassword(AuthRequest authRequest);

    String giveAccessToUser(UUID uuid, String userRole, Principal principal);


}
