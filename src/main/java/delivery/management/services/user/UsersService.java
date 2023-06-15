package delivery.management.services.user;




import delivery.management.dto.ApiResponse;
import delivery.management.model.dto.request.othersRequest.AuthRequest;
import delivery.management.model.dto.request.userRequest.UsersRequest;
import delivery.management.model.dto.request.userRequest.changeUserPasswordRequest;
import delivery.management.model.dto.request.userRequest.changeUsersPasswordRequest;
import delivery.management.model.dto.request.userRequest.loginUsersRequest;
import delivery.management.model.dto.response.userResponse.UsersResponse;

import javax.mail.MessagingException;
import java.util.List;
import java.util.UUID;


public interface UserService {

    ApiResponse<List<UsersResponse>> getListOfUsers(int page, int size);

    ApiResponse<String> addUsers(UsersRequest request);

    ApiResponse<UsersResponse> getUsersById(UUID userId);

    ApiResponse<String> updateUsers( UUID userId, UsersRequest request);

    ApiResponse<String> deleteUsers(UUID userId);

    ApiResponse<String> resetUsersPassword(String email, changeUsersPasswordRequest request);

    ApiResponse<String> forgotUsersPassword(String email) throws MessagingException;

    String authenticate(AuthRequest authRequest);

    ApiResponse<String> loginUsers(String email, loginUsersRequest request);





}
