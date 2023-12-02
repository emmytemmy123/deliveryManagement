package Salvation.Clinic.services.users;


import Salvation.Clinic.model.dto.request.UserRequest.UserCategoryRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.UserResponse.UserCategoryResponse;

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
