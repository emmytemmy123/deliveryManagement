package Salvation.Clinic.services.Others;

import Salvation.Clinic.model.dto.request.Others.AuthRequest;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.UUID;

public interface JwtAuthenticationService {

    ResponseEntity<?> authenticateUsernameAndPassword(AuthRequest authRequest);

    String giveAccessToUser(UUID uuid, String userRole, Principal principal);



}
