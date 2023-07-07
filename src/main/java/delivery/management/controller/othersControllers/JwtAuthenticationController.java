package delivery.management.controller.othersControllers;


import delivery.management.dto.ApiResponse;
import delivery.management.model.dto.request.othersRequest.AuthRequest;
import delivery.management.model.dto.response.othersResponse.AuthResponse;
import delivery.management.repo.user.UsersRepository;
import delivery.management.services.others.JwtAuthenticationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

import static delivery.management.utills.EndPoints.UsersEndPoints.*;

@RestController
@RequiredArgsConstructor
public class JwtAuthenticationController {


    private final JwtAuthenticationService jwtAuthenticationService;



    @PostMapping(AUTHENTICATE_USERS)
    @ApiOperation(value = "Endpoint for authenticate Username And Password2 ", response = String.class)
    public ResponseEntity authenticateUsernameAndPassword2(@Valid @RequestBody AuthRequest request) throws IOException {
        return jwtAuthenticationService.authenticateUsernameAndPassword(request);
    }



    @GetMapping(GIVE_ACCESS_TO_USERS)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    @ApiOperation(value = "Endpoint for giving access to users")
    public String giveAccessToUser(@PathVariable UUID uuid, @PathVariable String userRole, Principal principal) {
        return jwtAuthenticationService.giveAccessToUser(uuid, userRole,principal);
    }






}
