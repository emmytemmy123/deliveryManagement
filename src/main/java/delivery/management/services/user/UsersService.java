package delivery.management.services.user;


import delivery.management.dto.ApiResponse;
import delivery.management.model.dto.request.othersRequest.AuthRequest;
import delivery.management.model.dto.request.userRequest.UsersRequest;
import delivery.management.model.dto.request.userRequest.changeUserPasswordRequest;
import delivery.management.model.dto.response.othersResponse.AuthResponse;
import delivery.management.model.dto.response.userResponse.UsersResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


public interface UsersService {

    ApiResponse<List<UsersResponse>> getListOfUsers(int page, int size);

    ApiResponse<String> addUsers(UsersRequest request) throws IOException;

    ApiResponse<UsersResponse> getUsersById(UUID userId);

    ApiResponse<String> updateUsers(UUID userUuid, UsersRequest request);

    ApiResponse<String> updateUsersPhoto( UUID userUuid,  MultipartFile photoFile);

//    ApiResponse<String> updateUsersByUsername( String username, UsersRequest request);

    ApiResponse<String> deleteUsers(UUID userId);

    ApiResponse<String> resetUsersPassword(String email, changeUserPasswordRequest request);

    ApiResponse<String> forgotUsersPassword(String email) throws MessagingException;

    ApiResponse<AuthResponse> loginUsers(AuthRequest request);

    ApiResponse<UsersResponse> getUsersByUsername(String username);

    ApiResponse<UsersResponse> getUsersByEmail(String email);






}
