package Salvation.Clinic.controller.Others;


import Salvation.Clinic.model.dto.request.Others.AuthRequest;
import Salvation.Clinic.repo.userRepo.UserRepo;
import Salvation.Clinic.services.Others.JwtAuthenticationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

import static Salvation.Clinic.EndPoints.UsersEndPoints.AUTHENTICATE_USER;
import static Salvation.Clinic.EndPoints.UsersEndPoints.GIVE_ACCESS_TO_USER;

@RestController
@RequiredArgsConstructor
public class JwtAuthenticationController {

    private final UserRepo usersRepository;

    private final JwtAuthenticationService jwtAuthenticationService;




    @PostMapping(AUTHENTICATE_USER)
    @ApiOperation(value = "Endpoint for authenticate Username And Password ", response = String.class)
    public ResponseEntity<?> authenticateUsernameAndPassword(@Valid @RequestBody AuthRequest request) throws IOException {
        return jwtAuthenticationService.authenticateUsernameAndPassword(request);
    }


    @GetMapping(GIVE_ACCESS_TO_USER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    @ApiOperation(value = "Endpoint for giving access to users")
    public String giveAccessToUser(@PathVariable UUID uuid, @PathVariable String userRole, Principal principal) {
        return jwtAuthenticationService.giveAccessToUser(uuid, userRole,principal);
    }






}
