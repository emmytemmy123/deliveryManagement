package delivery.management.services.user;

import delivery.management.dto.ApiResponse;
import delivery.management.model.dto.request.userRequest.UserTypeRequest;
import delivery.management.model.dto.response.UserTypeResponse;

import java.util.List;
import java.util.UUID;

public interface UserTypeService {

    ApiResponse<List<UserTypeResponse>> getListOfUsersCategory(int page, int size);

    ApiResponse<String> addUsersCategory(UserTypeRequest request);

    ApiResponse<UserTypeResponse> getUsersTypeById(UUID usersCategoryId);

    ApiResponse<UserTypeResponse> getUsersTypeByName(String name);

    ApiResponse<String> updateUsersCategory( UUID usersCategoryId, UserTypeRequest request);

    ApiResponse<String> deleteUsersCategory(UUID usersCategoryId);


}
