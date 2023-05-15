package delivery.management.controller.othersControllers;


import delivery.management.model.dto.request.othersRequest.AuthRequest;
import delivery.management.repo.user.UserRepository;
import delivery.management.services.others.JwtAuthenticationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

import static delivery.management.utills.EndPoints.UsersEndPoints.*;

@RestController
@RequiredArgsConstructor
public class JwtAuthenticationController {

    private final UserRepository userRepository;

    private final JwtAuthenticationService jwtAuthenticationService;




    @PostMapping(AUTHENTICATE_USER)
    @ApiOperation(value = "Endpoint for authenticate Username And Password ", response = String.class)
    public String authenticateUsernameAndPassword(@Valid @RequestBody AuthRequest request) throws IOException {
        return jwtAuthenticationService.authenticateUsernameAndPassword(request);
    }


    @GetMapping(GIVE_ACCESS_TO_USER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    @ApiOperation(value = "Endpoint for giving access to users")
    public String giveAccessToUser(@PathVariable UUID uuid, @PathVariable String userRole, Principal principal) {
        return jwtAuthenticationService.giveAccessToUser(uuid, userRole,principal);
    }






}
