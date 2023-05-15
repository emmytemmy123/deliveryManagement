package delivery.management.services.others;

import delivery.management.common.UserConstant;
import delivery.management.exception.RecordNotFoundException;
import delivery.management.model.dto.request.othersRequest.AuthRequest;
import delivery.management.model.entity.user.AppUser;
import delivery.management.repo.user.UserRepository;
import delivery.management.utills.JwtUtil;
import delivery.management.utills.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class JwtAuthenticationServiceImpl implements JwtAuthenticationService{

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;



    @Override
    public String authenticateUsernameAndPassword(AuthRequest authRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authentication.isAuthenticated())

            return jwtUtil.generateToken(authRequest.getUsername(), authRequest.getPassword());

        throw new UsernameNotFoundException("invalid user request !");

    }


    /**
     * @validating userOptional by uuid*
     * @Validate if the List of user is empty otherwise return record not found
     * @return userOptional* *
     * * */
    private AppUser validateUser(UUID uuid) {
        Optional<AppUser> userOptional = userRepository.findByUuid(uuid);
        if (userOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return userOptional.get();
    }


    @Override
    public String giveAccessToUser(UUID uuid, String userRole, Principal principal) {

        AppUser user = validateUser(uuid);

        List<String> activeRoles = getRolesByLoggedInUser(principal);

        String newRole = "";
        if (activeRoles.contains(userRole)) {
            newRole = user.getRoles() + "," + userRole;
            user.setRoles(newRole);
        }
        userRepository.save(user);

        return "Hi " + user.getUsername() + " New Role assign to you by " + principal.getName();


    }



    private List<String> getRolesByLoggedInUser(Principal principal) {

        String roles = getNameOfLoggedInUser(principal).getRoles();

        List<String> assignRoles = Arrays.stream(roles.split(",")).collect(Collectors.toList());

        if (assignRoles.contains("ROLE_ADMIN")) {
            return Arrays.stream(UserConstant.ADMIN_ACCESS).collect(Collectors.toList());
        }
        if (assignRoles.contains("ROLE_MODERATOR")) {
            return Arrays.stream(UserConstant.MODERATOR_ACCESS).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }


    private AppUser getNameOfLoggedInUser(Principal principal) {
        return userRepository.findByUsername(principal.getName()).get();
    }









}
