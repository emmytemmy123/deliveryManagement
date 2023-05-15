package delivery.management.services.user;

import delivery.management.model.dto.request.userRequest.CustomerRequest;
import delivery.management.model.dto.request.userRequest.changeCustomerPasswordRequest;
import delivery.management.model.dto.request.userRequest.loginCustomerRequest;
import delivery.management.model.dto.response.othersResponse.ApiResponse;
import delivery.management.model.dto.response.userResponse.CustomerResponse;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


public interface CustomerService {

    ApiResponse<List<CustomerResponse>> getListOfCustomer(int page, int size);

    ApiResponse<String> addCustomer(CustomerRequest request) throws IOException;

    ApiResponse<CustomerResponse> getCustomerById(UUID customerId);

    ApiResponse<String> updateCustomer( UUID customerId, CustomerRequest request);

    ApiResponse<String> deleteCustomer(UUID customerId);

    ApiResponse<String> resetCustomerPassword(String email, changeCustomerPasswordRequest request);

    /**
     * @Validating existingCustomer by Email
     * @Validate customer password and change password
     * @Save the new password
     * @Return a Success Message if oldPassword is correct
     * @Return a Failed Message if oldPassword is Incorrect
     */
    ApiResponse<String> resetPassword(String email, changeCustomerPasswordRequest request);

    ApiResponse<String> forgotCustomerPassword(String email) throws MessagingException;

    ApiResponse<String> loginCustomer(String email, loginCustomerRequest request);



}
