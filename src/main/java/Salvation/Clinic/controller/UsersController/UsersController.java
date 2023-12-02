package Salvation.Clinic.controller.UsersController;

import Salvation.Clinic.model.dto.request.Others.AuthRequest;
import Salvation.Clinic.model.dto.request.UserRequest.UserCategoryRequest;
import Salvation.Clinic.model.dto.request.UserRequest.UsersRequest;
import Salvation.Clinic.model.dto.request.UserRequest.changeUserPasswordRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.Others.AuthResponse;
import Salvation.Clinic.model.dto.response.UserResponse.UserCategoryResponse;
import Salvation.Clinic.model.dto.response.UserResponse.UsersResponse;
import Salvation.Clinic.services.users.UserCategoryService;
import Salvation.Clinic.services.users.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static Salvation.Clinic.EndPoints.EndpointParam.*;
import static Salvation.Clinic.EndPoints.UsersEndPoints.*;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(users)
@RestController
@RequiredArgsConstructor
public class UsersController  {

    private final UserService userService;
    private final UserCategoryService userCategoryService;


                                        //FIND_LISTS_OF_USERS


    @GetMapping(FIND_USER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    @ApiOperation(value = "Endpoint for retrieving lists of user", response = UsersResponse.class, responseContainer = "List")
    public ApiResponse<List<UsersResponse>> getListOfUsers(@RequestParam(value=PAGE, defaultValue = PAGE_DEFAULT) int page,
                                                           @RequestParam(value=SIZE,defaultValue=SIZE_DEFAULT) int size) {
        return userService.getListOfUsers(page,size);
    }


    @GetMapping(FIND_CATEGORY)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    @ApiOperation(value = "Endpoint for retrieving lists of usertype", response = UserCategoryResponse.class, responseContainer = "List")
    public ApiResponse<List<UserCategoryResponse>> getListOfUserCategory(@RequestParam(value=PAGE, defaultValue = PAGE_DEFAULT) int page,
                                                          @RequestParam(value=SIZE,defaultValue=SIZE_DEFAULT) int size) {
        return userCategoryService.getListOfUsersCategory(page,size);
    }



                                    //ADD_USERS

    @PostMapping(ADD_USER)
    @ApiOperation(value = "Endpoint for adding new user to database", response = String.class)
    public ApiResponse<String> addUsers(@Valid @RequestBody UsersRequest request) throws IOException {
        return userService.addUsers(request);
    }

    @PostMapping(ADD_CATEGORY)
    @ApiOperation(value = "Endpoint for adding new userCategory to database", response = String.class)
    public ApiResponse<String> addUsersCategory(@Valid @RequestBody UserCategoryRequest request) {
        return userCategoryService.addUsersCategory(request);
    }


                                                        //FIND_USERS_BY_ID

    @GetMapping(FIND_USER_BY_ID)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching user by id from database", response = UsersResponse.class)
    public ApiResponse<UsersResponse> getUsersById(@PathVariable(value = "uuid") UUID userUuid) {
        return userService.getUsersById(userUuid);
    }


                                                        //UPDATE_USERS

    @PutMapping(UPDATE_USER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for updating user by id from database", response = String.class)
    public ApiResponse<String> updateUsers(@PathVariable(value = "uuid") UUID userUuid, @RequestBody UsersRequest request) {
        return userService.updateUsers(userUuid, request);
    }


    @PutMapping(UPDATE_CATEGORY)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for updating user Category by id from database", response = String.class)
    public ApiResponse<String> updateUsersCategory(@PathVariable(value = "uuid") UUID userCategoryUuid, @RequestBody UserCategoryRequest request) {
        return userCategoryService.updateUsersCategory(userCategoryUuid, request);
    }



                                                     //DELETE_USERS

    @DeleteMapping(DELETE_USER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') ")
    @ApiOperation(value = "Endpoint for deleting user by id from database", response = String.class)
    public ApiResponse<String> deleteUser(@PathVariable(value = "uuid") UUID userUuid) {
        return userService.deleteUsers(userUuid);
    }


    @DeleteMapping(DELETE_CATEGORY)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') ")
    @ApiOperation(value = "Endpoint for deleting userCategory by id from database", response = String.class)
    public ApiResponse<String> deleteUserCategory(@PathVariable(value = "uuid") UUID userCategoryUuid) {
        return userCategoryService.deleteUsersCategory(userCategoryUuid);
    }


                                                 //Change Password

    @PutMapping(RESET_USER_PASSWORD)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for resetting users password from database", response = String.class)
    public ApiResponse<String> resetPassword(@RequestBody changeUserPasswordRequest request, String email) {
        return userService.resetUsersPassword(email, request);
    }



                                                 //Forgot Password
    @GetMapping(FORGOT_USER_PASSWORD)
    @PreAuthorize("hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for getting forgotten users password from database", response = String.class)
    public ApiResponse<String> forgotUserPassword(String email) throws MessagingException {
        return userService.forgotUsersPassword(email);
    }


                                        //login Users
    @PostMapping(LOGIN_USER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint to login users to database", response = String.class)
    public ApiResponse<AuthResponse> loginUser(@RequestBody AuthRequest request) {

        return userService.loginUsers( request);
    }





}
