package delivery.management.controller.usersControllers;

import delivery.management.model.dto.request.userRequest.*;
import delivery.management.model.dto.response.othersResponse.ApiResponse;
import delivery.management.model.dto.response.userResponse.CustomerResponse;
import delivery.management.model.dto.response.userResponse.DriverResponse;
import delivery.management.model.dto.response.userResponse.UserResponse;
import delivery.management.services.user.DriverService;
import io.swagger.annotations.ApiOperation;
import delivery.management.model.dto.response.userResponse.SenderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import delivery.management.services.user.CustomerService;
import delivery.management.services.user.SenderService;
import delivery.management.services.user.UserService;
import delivery.management.utills.EndpointParam;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static delivery.management.utills.EndPoints.UsersEndPoints.*;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(users)
@RestController
@RequiredArgsConstructor
public class UsersController  {

    private final CustomerService customerService;
    private final SenderService senderService;
    private final UserService userService;
    private final DriverService driverService;
 


                                        //FIND_LISTS_OF_USERS
    @GetMapping(FIND_CUSTOMER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    @ApiOperation(value = "Endpoint for retrieving lists of customer", response = CustomerResponse.class, responseContainer = "List")
    public ApiResponse<List<CustomerResponse>> getListOfCustomer(@RequestParam(value= EndpointParam.PAGE, defaultValue = EndpointParam.PAGE_DEFAULT) int page,
                                                                 @RequestParam(value= EndpointParam.SIZE,defaultValue= EndpointParam.SIZE_DEFAULT) int size) {
        return customerService.getListOfCustomer(page,size);
    }

    @GetMapping(FIND_SENDER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    @ApiOperation(value = "Endpoint for retrieving lists of sender", response = SenderResponse.class, responseContainer = "List")
    public ApiResponse<List<SenderResponse>> getListOfSender(@RequestParam(value= EndpointParam.PAGE, defaultValue = EndpointParam.PAGE_DEFAULT) int page,
                                                             @RequestParam(value= EndpointParam.SIZE,defaultValue= EndpointParam.SIZE_DEFAULT) int size) {
        return senderService.getListOfSender(page,size);
    }


    @GetMapping(FIND_USER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    @ApiOperation(value = "Endpoint for retrieving lists of user", response = UserResponse.class, responseContainer = "List")
    public ApiResponse<List<UserResponse>> getListOfUsers(@RequestParam(value= EndpointParam.PAGE, defaultValue = EndpointParam.PAGE_DEFAULT) int page,
                                                          @RequestParam(value= EndpointParam.SIZE,defaultValue= EndpointParam.SIZE_DEFAULT) int size) {
        return userService.getListOfUsers(page,size);
    }

    @GetMapping(FIND_DRIVER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    @ApiOperation(value = "Endpoint for retrieving lists of driver", response = DriverResponse.class, responseContainer = "List")
    public ApiResponse<List<DriverResponse>> getListOfDriver(@RequestParam(value= EndpointParam.PAGE, defaultValue = EndpointParam.PAGE_DEFAULT) int page,
                                                          @RequestParam(value= EndpointParam.SIZE,defaultValue= EndpointParam.SIZE_DEFAULT) int size) {
        return driverService.getListOfDriver(page,size);
    }


                                    //ADD_USERS
    @PostMapping(ADD_CUSTOMER)
    @ApiOperation(value = "Endpoint for adding new customer to database", response = String.class)
    public ApiResponse<String> addCustomer(@Valid @RequestBody CustomerRequest request) throws IOException {
        return customerService.addCustomer(request);
    }

    @PostMapping(ADD_SENDER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') ")
    @ApiOperation(value = "Endpoint for adding new sender to database", response = String.class)
    public ApiResponse<String> addSender(@Valid @RequestBody SenderRequest request) {
        return senderService.addSender(request);
    }

    @PostMapping(ADD_USER)
    @ApiOperation(value = "Endpoint for adding new user to database", response = String.class)
    public ApiResponse<String> addUsers(@Valid @RequestBody UserRequest request) {
        return userService.addUsers(request);
    }

    @PostMapping(ADD_DRIVER)
    @ApiOperation(value = "Endpoint for adding new driver to database", response = String.class)
    public ApiResponse<String> addDriver(@Valid @RequestBody DriverRequest request) throws IOException {
        return driverService.addDriver(request);
    }


                                         //FIND_USERS_BY_ID
    @GetMapping(FIND_CUSTOMER_BY_ID)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching customer by id from database", response = CustomerResponse.class)
    public ApiResponse<CustomerResponse> getCustomerById(@PathVariable(value = "id") UUID customer_id) {
        return customerService.getCustomerById(customer_id);
    }

    @GetMapping(FIND_SENDER_BY_ID)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching sender by id from database", response = SenderResponse.class)
    public ApiResponse<SenderResponse> getSenderById(@PathVariable(value = "id") UUID senderId) {
        return senderService.getSenderById(senderId);
    }

    @GetMapping(FIND_USER_BY_ID)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching user by id from database", response = UserResponse.class)
    public ApiResponse<UserResponse> getUsersById(@PathVariable(value = "id") UUID user_id) {
        return userService.getUsersById(user_id);
    }

    @GetMapping(FIND_DRIVER_BY_ID)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching driver by id from database", response = DriverResponse.class)
    public ApiResponse<DriverResponse> getDriverById(@PathVariable(value = "id") UUID driverId) {
        return driverService.getDriverById(driverId);
    }


                                        //UPDATE_USERS
    @PutMapping(UPDATE_CUSTOMER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for updating customer by id from database", response = String.class)
    public ApiResponse<String> updateCustomer(@PathVariable(value = "id") UUID customerId,
                                              @RequestBody CustomerRequest request) {
        return customerService.updateCustomer(customerId, request);
    }

    @PutMapping(UPDATE_SENDER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for updating sender by id from database", response = String.class)
    public ApiResponse<String> updateSender(@PathVariable(value = "id") UUID senderId,
                                              @RequestBody SenderRequest request) {
        return senderService.updateSender(senderId, request);
    }

    @PutMapping(UPDATE_USER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for updating user by id from database", response = String.class)
    public ApiResponse<String> updateUsers(@PathVariable(value = "id") UUID userId,
                                           @RequestBody UserRequest request) {
        return userService.updateUser(userId, request);
    }

    @PutMapping(UPDATE_DRIVER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for updating driver by id from database", response = String.class)
    public ApiResponse<String> updateDriver(@PathVariable(value = "id") UUID driverId,
                                           @RequestBody DriverRequest request) {
        return driverService.updateDriver(driverId, request);
    }



                                            //DELETE_USERS

    @DeleteMapping(DELETE_USER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') ")
    @ApiOperation(value = "Endpoint for deleting user by id from database", response = String.class)
    public ApiResponse<String> deleteUser(@PathVariable(value = "id") UUID user_id) {
        return userService.deleteUser(user_id);
    }



                                //Change Password

    @PutMapping(RESET_USER_PASSWORD)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for resetting users password from database", response = String.class)
    public ApiResponse<String> resetPassword(@RequestBody changeUserPasswordRequest request, String email) {
        return userService.resetUserPassword(email, request);
    }

    @PutMapping(RESET_CUSTOMER_PASSWORD)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for updating customer password from database", response = String.class)
    public ApiResponse<String> changeCustomerPassword(@RequestBody changeCustomerPasswordRequest request, String email) {
        return customerService.resetCustomerPassword(email, request);
    }

    @PutMapping(RESET_SENDER_PASSWORD)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for resetting sender password from database", response = String.class)
    public ApiResponse<String> resetPassword(@RequestBody changeSenderPasswordRequest request, String email) {
        return senderService.resetSenderPassword(email, request);
    }

    @PutMapping(RESET_DRIVER_PASSWORD)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for resetting driver password from database", response = String.class)
    public ApiResponse<String> resetDriverPassword(@RequestBody changeDriverPasswordRequest request, String email) {
        return driverService.resetDriverPassword(email, request);
    }



                                        //Forgot Password
    @GetMapping(FORGOT_USER_PASSWORD)
    @PreAuthorize("hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for getting forgotten users password from database", response = String.class)
    public ApiResponse<String> forgotUserPassword(String email) throws MessagingException {
        return userService.forgotUserPassword(email);
    }

    @GetMapping(FORGOT_CUSTOMER_PASSWORD)
    @PreAuthorize(" hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for resetting customer password from database", response = String.class)
    public ApiResponse<String> forgotCustomerPassword(String email) throws MessagingException {
        return customerService.forgotCustomerPassword(email);
    }

    @GetMapping(FORGOT_SENDER_PASSWORD)
    @PreAuthorize("hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for getting forgotten SENDER password from database", response = String.class)
    public ApiResponse<String> forgotSenderPassword(String email) throws MessagingException {
        return senderService.forgotSenderPassword(email);
    }

    @GetMapping(FORGOT_DRIVER_PASSWORD)
    @PreAuthorize("hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for getting forgotten DRIVER password from database", response = String.class)
    public ApiResponse<String> forgotDriverPassword(String email) throws MessagingException {
        return driverService.forgotDriverPassword(email);
    }



                                        //login Users
    @PostMapping(LOGIN_USER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint to login users to database", response = String.class)
    public ApiResponse<String> loginUser(@RequestBody loginUserRequest request, String email) {
        return userService.loginUser(email, request);
    }

    @PostMapping(LOGIN_CUSTOMER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint to login customer to database", response = String.class)
    public ApiResponse<String> loginCustomer(@RequestBody loginCustomerRequest request, String email) {
        return customerService.loginCustomer(email, request);
    }

    @PostMapping(LOGIN_SENDER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint to login SENDER to database", response = String.class)
    public ApiResponse<String> loginSender(@RequestBody loginSenderRequest request, String email) {
        return senderService.loginSender(email, request);
    }

    @PostMapping(LOGIN_DRIVER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint to login DRIVER to database", response = String.class)
    public ApiResponse<String> loginDriver(@RequestBody loginDriverRequest request, String email) {
        return driverService.loginDriver(email, request);
    }



}
