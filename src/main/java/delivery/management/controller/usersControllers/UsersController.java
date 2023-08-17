package delivery.management.controller.usersControllers;

import delivery.management.dto.ApiResponse;
import delivery.management.model.dto.request.othersRequest.AuthRequest;
import delivery.management.model.dto.request.userRequest.*;
import delivery.management.model.dto.response.othersResponse.AuthResponse;
import delivery.management.model.dto.response.userResponse.UserCategoryResponse;
import delivery.management.services.user.UserCategoryService;
import delivery.management.services.user.UsersService;
import io.swagger.annotations.ApiOperation;
import delivery.management.model.dto.response.userResponse.UsersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static delivery.management.utills.EndPoints.UsersEndPoints.*;
import static delivery.management.utills.EndpointParam.*;

@RequestMapping(users)
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UsersController  {

    private final UsersService usersService;
    private final UserCategoryService userCategoryService;



                                        //FIND_LISTS_OF_USERS

    @GetMapping(FIND_USERS)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    @ApiOperation(value = "Endpoint for retrieving lists of users", response = UsersResponse.class, responseContainer = "List")
    public delivery.management.dto.ApiResponse<List<UsersResponse>> getListOfUsers(@RequestParam(value= PAGE, defaultValue = PAGE_DEFAULT) int page,
                                                                                   @RequestParam(value= SIZE,defaultValue= SIZE_DEFAULT) int size) {
        return usersService.getListOfUsers(page,size);
    }


    @GetMapping(FIND_USERTYPE)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    @ApiOperation(value = "Endpoint for retrieving lists of userType", response = UserCategoryResponse.class, responseContainer = "List")
    public ApiResponse<List<UserCategoryResponse>> getListOfUserType(@RequestParam(value= PAGE, defaultValue = PAGE_DEFAULT) int page,
                                                                 @RequestParam(value= SIZE,defaultValue= SIZE_DEFAULT) int size) {
        return userCategoryService.getListOfUsersCategory(page,size);
    }



                                    //ADD_USERS

    @PostMapping(ADD_USERS)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') ")
    @ApiOperation(value = "Endpoint for adding new user to database", response = String.class,
            produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<String> addUsers(@Valid @RequestBody UsersRequest request) throws IOException {
        return usersService.addUsers(request);
    }


    @PostMapping(ADD_USERTYPE)
    @ApiOperation(value = "Endpoint for adding new userType to database", response = String.class)
    public ApiResponse<String> addUserType(@Valid @RequestBody UserCategoryRequest request) {

        return userCategoryService.addUsersCategory(request);
    }




                                         //FIND_USERS_BY_ID

    @GetMapping(FIND_USERS_BY_ID)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching sender by id from database", response = UsersResponse.class)
    public ApiResponse<UsersResponse> getUsersById(@PathVariable(value = "id") UUID usersId) {
        return usersService.getUsersById(usersId);
    }

    @GetMapping(FIND_USERTYPE_BY_ID)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching userType by id from database", response = UserCategoryResponse.class)
    public ApiResponse<UserCategoryResponse> getUserTypeById(@PathVariable(value = "id") UUID userTypeId) {
        return userCategoryService.getUsersTypeById(userTypeId);
    }

                                    //FIND USERTYPE BY NAME
    @GetMapping(FIND_USERS_USERNAME)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching USERS by username from database", response = UsersResponse.class)
    public ApiResponse<UsersResponse> getUsersByUsername(@RequestParam String username) {
        return usersService.getUsersByUsername(username);
    }

    @GetMapping(FIND_USERTYPE_BY_NAME)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for retrieving lists of usertype by Name", response = UserCategoryResponse.class, responseContainer = "List")
    public ApiResponse<UserCategoryResponse> searchListOfUsersTypeByName(@RequestParam(value=PAGE, defaultValue = PAGE_DEFAULT) int page,
                                                                                             @RequestParam(value=SIZE,defaultValue=SIZE_DEFAULT) int size,
                                                                                             @RequestParam String name ) {
        return userCategoryService.getUsersTypeByName(name);
    }


                                        //FIND USERTYPE BY EMAIL
    @GetMapping(FIND_USERS_EMAIL)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
        @ApiOperation(value = "Endpoint for fetching USERS by email from database", response = UsersResponse.class)
    public ApiResponse<UsersResponse> getUsersByEmail(@RequestParam String email) {
        return usersService.getUsersByEmail(email);
    }


                                        //UPDATE_USERS

    @PutMapping(UPDATE_USERS)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for updating users by id from database", response = String.class)
    public ApiResponse<String> updateUsers(@PathVariable(value = "uuid") UUID userUuid,
                                           @RequestBody UsersRequest request) {
        return usersService.updateUsers(userUuid, request);
    }

    @PutMapping(UPDATE_USERS_PHOTO)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for updating usersPhoto by id from database", response = String.class)
    public ApiResponse<String> updateUsersPhoto(@PathVariable("uuid") UUID userUuid,
                                                @RequestParam(required = false) MultipartFile photo) {
        return usersService.updateUsersPhoto(userUuid, photo );
    }

    @PutMapping(UPDATE_USERTYPE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for updating userType by id from database", response = String.class)
    public ApiResponse<String> updateUserType(@PathVariable(value = "id") UUID userId,
                                                                      @RequestBody UserCategoryRequest request) {
        return userCategoryService.updateUsersCategory(userId, request);
    }




                                            //DELETE_USERS


    @DeleteMapping(DELETE_USERTYPE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') ")
    @ApiOperation(value = "Endpoint for deleting userType by id from database", response = String.class)
    public delivery.management.dto.ApiResponse<String> deleteUserType(@PathVariable(value = "id") UUID user_id) {
        return userCategoryService.deleteUsersCategory(user_id);
    }


                                //Change Password

    @PutMapping(RESET_USERS_PASSWORD)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for resetting users password from database", response = String.class)
    public delivery.management.dto.ApiResponse<String> resetUserPassword(@RequestBody changeUserPasswordRequest request, String email) {
        return usersService.resetUsersPassword(email, request);
    }



//    @PutMapping(RESET_USERS_PASSWORD)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
//    @ApiOperation(value = "Endpoint for resetting sender password from database", response = String.class)
//    public ApiResponse<String> resetUsersPassword(@RequestBody String pasword, String email) {
//        return usersService.resetUsersPassword(email, pasword);
//    }





                                        //Forgot Password
    @GetMapping(FORGOT_USERS_PASSWORD)
    @PreAuthorize("hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for getting forgotten users password from database", response = String.class)
    public delivery.management.dto.ApiResponse<String> forgotUserPassword(String email) throws MessagingException {
        return usersService.forgotUsersPassword(email);
    }



//    @GetMapping(FORGOT_USERS_PASSWORD)
//    @PreAuthorize("hasAuthority('ROLE_USER') ")
//    @ApiOperation(value = "Endpoint for getting forgotten SENDER password from database", response = String.class)
//    public ApiResponse<String> forgotUsersPassword(String email) throws MessagingException {
//        return usersService.forgotUsersPassword(email);
//    }



                                        //login Users

    @PostMapping(LOGIN_USERS)
//  @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint to login users to database", response = String.class)
    public ApiResponse<AuthResponse> loginUsers(@RequestBody AuthRequest request ) {
        return usersService.loginUsers(request);
    }





}
