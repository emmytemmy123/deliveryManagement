package delivery.management.services.user;


import delivery.management.common.UserConstant;
import delivery.management.dto.ApiResponse;
import delivery.management.exception.RecordNotFoundException;
import delivery.management.mapper.Mapper;
import delivery.management.model.dto.enums.AppStatus;
import delivery.management.model.dto.request.othersRequest.AuthRequest;
import delivery.management.model.dto.request.userRequest.UsersRequest;
import delivery.management.model.dto.request.userRequest.changeUserPasswordRequest;
import delivery.management.model.dto.response.othersResponse.AuthResponse;
import delivery.management.model.dto.response.userResponse.UsersResponse;
import delivery.management.model.entity.user.Users;
import delivery.management.model.entity.user.UsersType;
import delivery.management.repo.user.UsersRepository;
import delivery.management.repo.user.UsersTypeRepository;
import delivery.management.services.others.JwtAuthenticationServiceImpl;
import delivery.management.utills.EmailUtils;
import delivery.management.utills.JwtUtil;
import delivery.management.utills.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final UsersTypeRepository usersTypeRepository;
    private final EmailUtils emailUtils;
    private final JwtAuthenticationServiceImpl jwtAuthenticationService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    String username;


    @Override
    /**
     * @Finding the list of Users
     * @Validate if the List of users is empty otherwise return record not found
     * @return the list of users and a Success Message*
     * * */
    public ApiResponse<List<UsersResponse>> getListOfUsers(int page, int size) {
            List<Users> userList = usersRepository.findAll(PageRequest.of(page, size)).toList();
            if (userList.isEmpty())
                throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

            return new ApiResponse(AppStatus.SUCCESS.label, Mapper.convertList(userList, UsersResponse.class));
        }


    /**
     * @validating userOptional by uuid*
     * @Validate if the List of user is empty otherwise return record not found
     * @return userOptional* *
     * * */
    private Users validateUser(UUID uuid) {
        Optional<Users> userOptional = usersRepository.findByUuid(uuid);
        if (userOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return userOptional.get();
    }


    /**
     * @Validating existingUsersOptional by email
     * @Validate if the List of existingUsersOptional is empty otherwise return Duplicate Record*
     * * */
    private void validateDuplicationUsers(String email){
        Optional<Users> existingUsersOptional = usersRepository.findByEmailId(email);
        if(existingUsersOptional.isPresent())
            throw new RecordNotFoundException("Email Already Exist");

    }


    /**
     * @return
     * @Validating existingUsersOptional by username
     * @Validate if the List of existingUsersOptional is empty otherwise return Duplicate Record*
     * *
     */
    private Users validateUsersByUseraname(String username){
        Optional<Users> existingUsersOptional = usersRepository.findUsersByUsername2(username);
        if(existingUsersOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return existingUsersOptional.get();
    }


    @Override
    /**
     * @Validate that no duplicate users is allowed*
     * @Validate that user exists otherwise return record not found*
     * Create user definition and save
     * @return success message
     * * */
    public ApiResponse<String> addUsers(UsersRequest request) {

        validateDuplicationUsers(request.getEmail());

        UsersType existingUsersType = usersTypeRepository.findByName(request.getAccountType())
                .orElseThrow(()->new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        Users user = new Users();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setAddress(request.getAddress());
        user.setCountry(request.getCountry());
        user.setCity(request.getCity());
        user.setGender(request.getGender());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setUsername(request.getUsername());
        user.setPhoto(request.getPhoto());
        user.setUsersType(existingUsersType);
        user.setUsersCategory(existingUsersType.getName());
        user.setRoles(UserConstant.DEFAULT_ROLE);//USER


        usersRepository.save(user);

            return new ApiResponse("Record Added successfully", AppStatus.SUCCESS.label,
                    HttpStatus.OK.value());
        }


    @Override
    /**
     * @Finding the list of all userOptional by uuid*
     * @Validate if the List of userOptional is empty otherwise return record not found
     * Create the user definition and get the user Optional
     * @return the list of users and a Success Message* *
     * * */
    public ApiResponse<UsersResponse> getUsersById( UUID userId) {
//        if (jwtFilter.isAdmin()) {
            Optional<Users> userOptional = usersRepository.findByUuid(userId);
            if (userOptional.isEmpty())
                throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        Users appUser = userOptional.get();

            return new ApiResponse<UsersResponse>(AppStatus.SUCCESS.label,
                    Mapper.convertObject(appUser, UsersResponse.class));
        }
//        return new ApiResponse(AppStatus.REJECT.label, HttpStatus.EXPECTATION_FAILED.value(),
//                "You are not Authorized");
//    }





//    private AppUser postedByUuid(String postedBy) {
//        AppUser user = validateUser(UUID.fromString(postedBy));
//        AppUser users = new AppUser();
//        users.setPostedBy(String.valueOf(users));
//        return user;
//    }


    @Override
    /**
     * @validating userOptional by uuid
     * @Validate if the List of user is empty otherwise return record not found
     * Create the user definition and update
     * @return a Success Message
     * * */
    public ApiResponse<String> updateUsers(UUID userUuid, UsersRequest request) {

            Users user = validateUser(userUuid);

        if (request.getName() != null) {
            user.setName(request.getName());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getAddress() != null) {
            user.setAddress(request.getAddress());
        }
        if (request.getCountry() != null) {
            user.setCountry(request.getCountry());
        }
        if (request.getCity() != null) {
            user.setCity(request.getCity());
        }
        if (request.getGender() != null) {
            user.setGender(request.getGender());
        }
        if (request.getPhone() != null) {
            user.setPhone(request.getPhone());
        }
        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }
        if (request.getPhoto() != null) {
            user.setPhoto(request.getPhoto());
        }
        if (request.getDriverLicense() != null) {
            user.setDriverLicense(request.getDriverLicense());
        }


        usersRepository.save(user);
            return new ApiResponse<>(AppStatus.SUCCESS.label, "Record Updated Successfully");
        }


    /**
     * @validating userOptional by uuid
     * @Validate if the List of user is empty otherwise return record not found
     * Create the user definition and update
     * @return a Success Message
     * * */
//    @Override
//    public ApiResponse<String> updateUsersByUsername(String username, UsersRequest request) {
//
//        Users user = validateUsersByUseraname(username);
//
//        if (request.getName() != null) {
//            user.setName(request.getName());
//        }
//        if (request.getEmail() != null) {
//            user.setEmail(request.getEmail());
//        }
//        if (request.getAddress() != null) {
//            user.setAddress(request.getAddress());
//        }
//        if (request.getCountry() != null) {
//            user.setCountry(request.getCountry());
//        }
//        if (request.getCity() != null) {
//            user.setCity(request.getCity());
//        }
//        if (request.getGender() != null) {
//            user.setGender(request.getGender());
//        }
//        if (request.getPhone() != null) {
//            user.setPhone(request.getPhone());
//        }
//        if (request.getUsername() != null) {
//            user.setUsername(request.getUsername());
//        }
//        if (request.getPhoto() != null) {
//            user.setPhoto(request.getPhoto());
//        }
//        if (request.getDriverLicense() != null) {
//            user.setDriverLicense(request.getDriverLicense());
//        }
//
//
//        usersRepository.save(user);
//        return new ApiResponse<>(AppStatus.SUCCESS.label, "Record Updated Successfully");
//
//
//    }
//


    @Override
    /**
     * @validating user by uuid
     * @Validate if user is empty otherwise return record not found
     * @Delete user
     * @return a Success Message
     * * */
    public ApiResponse<String> deleteUsers(UUID userId) {
//        if (jwtFilter.isAdmin()) {
            Users user = validateUser(userId);
            usersRepository.delete(user);
            return new ApiResponse(AppStatus.SUCCESS.label, "Record Deleted successfully");
        }
//        return new ApiResponse(AppStatus.REJECT.label, HttpStatus.EXPECTATION_FAILED.value(),
//                "You are not Authorized");
//    }

    @Override
    /**
     * @Validating existingUser by Email
     * @Validate user password and change password
     * @Save the new password and return a Success Message
     */
    public ApiResponse<String> resetUsersPassword(String email, changeUserPasswordRequest request) {
//        if (jwtFilter.isAdmin()) {
            Users users = usersRepository.findByEmail(email);

            if (users.getPassword().equals(request.getOldPassword())) {
                users.setPassword(request.getNewPassword());

                usersRepository.save(users);

                return new ApiResponse("Password Changed successfully", AppStatus.SUCCESS.label,
                        HttpStatus.OK.value());
            }
            return new ApiResponse("Incorrect Old Password", AppStatus.FAILED.label,
                    HttpStatus.BAD_REQUEST.value());
        }
//        return new ApiResponse(AppStatus.REJECT.label, HttpStatus.EXPECTATION_FAILED.value(),
//                "You are not Authorized");
//    }

    @Override
    /**
     * @Validating existingUser by Email
     * @Getiing email and password of user and sending it to User`s Email
     * @Return a Success Message
     */
    public ApiResponse<String> forgotUsersPassword(String email) throws MessagingException {

        Users users = usersRepository.findByEmail(email);

        emailUtils.forgotMail(users.getEmail(), "Your Booking Credentials by Hotel Management System", users.getPassword());

        return new ApiResponse("Check your email for credentials", AppStatus.SUCCESS.label,
                HttpStatus.OK.value());

    }


    @Override
    /**
     * @Validating and finding existingUser by Email
     * @Validate user email and password
     * @Return a Success Message if email and password is correct
     * @Return a Failed Message if email and password is Incorrect
     */
    public ApiResponse <AuthResponse> loginUsers(AuthRequest request) {
        Users users = usersRepository.findByUsername(request.getUsername());

        if (users.getUsername().equals(request.getUsername()) && users.getPassword().equals(request.getPassword())) {

                return new ApiResponse<AuthResponse>(AppStatus.SUCCESS.label,
                        Mapper.convertObject(users, AuthResponse.class));
            }

        return new ApiResponse("Incorrect Email or Password", AppStatus.FAILED.label,
                HttpStatus.BAD_REQUEST.value());
    }

    @Override
    public ApiResponse<UsersResponse> getUsersByUsername(String username) {

    Optional<Users> existingUsersOption = usersRepository.findUsersByUsername(username);
    if(existingUsersOption.isEmpty())
        throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

    Users users = existingUsersOption.get();

    return  new ApiResponse<UsersResponse>(AppStatus.SUCCESS.label,
            Mapper.convertObject(users, UsersResponse.class));


    }

    @Override
    public ApiResponse<UsersResponse> getUsersByEmail(String email) {

        Optional<Users> existingUsersOption = usersRepository.findUsersByEmail(email);
        if(existingUsersOption.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        Users users = existingUsersOption.get();

        return  new ApiResponse<UsersResponse>(AppStatus.SUCCESS.label,
                Mapper.convertObject(users, UsersResponse.class));


    }

    private List<String> getRolesByLoggedInUser(Principal principal) {
        String roles = getLoggedInUser(principal).getRoles();
        List<String> assignRoles = Arrays.stream(roles.split(",")).collect(Collectors.toList());
        if (assignRoles.contains("ROLE_ADMIN")) {
            return Arrays.stream(UserConstant.ADMIN_ACCESS).collect(Collectors.toList());
        }
        if (assignRoles.contains("ROLE_MODERATOR")) {
            return Arrays.stream(UserConstant.MODERATOR_ACCESS).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private Users getLoggedInUser(Principal principal) {
        return usersRepository.findUsersByUsername(principal.getName()).get();
    }



}
