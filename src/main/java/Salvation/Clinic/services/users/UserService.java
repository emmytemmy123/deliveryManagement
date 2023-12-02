package Salvation.Clinic.services.users;


import Salvation.Clinic.model.dto.request.Others.AuthRequest;
import Salvation.Clinic.model.dto.request.UserRequest.UsersRequest;
import Salvation.Clinic.model.dto.request.UserRequest.changeUserPasswordRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.Others.AuthResponse;
import Salvation.Clinic.model.dto.response.UserResponse.UsersResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface UserService {

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
