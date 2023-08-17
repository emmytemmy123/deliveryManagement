package delivery.management.services.user;

import delivery.management.dto.ApiResponse;
import delivery.management.model.dto.request.userRequest.UserCategoryRequest;
import delivery.management.model.dto.response.userResponse.UserCategoryResponse;

import java.util.List;
import java.util.UUID;

public interface UserCategoryService {

    ApiResponse<List<UserCategoryResponse>> getListOfUsersCategory(int page, int size);

    ApiResponse<String> addUsersCategory(UserCategoryRequest request);

    ApiResponse<UserCategoryResponse> getUsersTypeById(UUID usersCategoryId);

    ApiResponse<UserCategoryResponse> getUsersTypeByName(String name);

    ApiResponse<String> updateUsersCategory( UUID usersCategoryId, UserCategoryRequest request);

    ApiResponse<String> deleteUsersCategory(UUID usersCategoryId);


}
