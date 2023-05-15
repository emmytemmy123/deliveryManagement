package delivery.management.services.user;


import delivery.management.model.dto.request.othersRequest.AuthRequest;
import delivery.management.model.dto.request.userRequest.UserRequest;
import delivery.management.model.dto.request.userRequest.changeUserPasswordRequest;
import delivery.management.model.dto.request.userRequest.loginUserRequest;
import delivery.management.model.dto.response.othersResponse.ApiResponse;
import delivery.management.model.dto.response.userResponse.UserResponse;

import javax.mail.MessagingException;
import java.util.List;
import java.util.UUID;


public interface UserService {

    ApiResponse<List<UserResponse>> getListOfUsers(int page, int size);

    ApiResponse<String> addUsers(UserRequest request);

    ApiResponse<UserResponse> getUsersById(UUID userId);

    ApiResponse<String> updateUser( UUID userId, UserRequest request);

    ApiResponse<String> deleteUser(UUID userId);

    ApiResponse<String> resetUserPassword(String email, changeUserPasswordRequest request);

    ApiResponse<String> forgotUserPassword(String email) throws MessagingException;

    String authenticate(AuthRequest authRequest);

    ApiResponse<String> loginUser(String email, loginUserRequest request);





}
