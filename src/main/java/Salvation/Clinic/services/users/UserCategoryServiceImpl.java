package Salvation.Clinic.services.users;


import Salvation.Clinic.mapper.Mapper;
import Salvation.Clinic.model.dto.enums.AppStatus;
import Salvation.Clinic.model.dto.request.UserRequest.UserCategoryRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.UserResponse.UserCategoryResponse;
import Salvation.Clinic.model.dto.response.exception.RecordNotFoundException;
import Salvation.Clinic.model.entity.users.UsersCategory;
import Salvation.Clinic.repo.userRepo.UserCategoryRepo;
import Salvation.Clinic.utills.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserCategoryServiceImpl implements UserCategoryService {

    private final UserCategoryRepo userCategoryRepo;


    @Override
    /**
     * @Validate and Find the list of all UsersCategory
     * @Validate if the List of UsersCategory is empty otherwise return record not found*
     * @return the list of UsersCategory and a Success Message* *
     * * */
    public ApiResponse<List<UserCategoryResponse>> getListOfUsersCategory(int page, int size) {
    List<UsersCategory> userCategoryServiceList = userCategoryRepo.findAll(PageRequest.of(page, size)).toList();
    if(userCategoryServiceList.isEmpty())
        throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

    return new ApiResponse<>(AppStatus.SUCCESS.label, Mapper.convertList(userCategoryServiceList, UsersCategory.class));
    }


    /**
     * @Validating existingUsersCategoryOptional by name
     * @Validate if the List of existingUsersCategoryOptional is empty otherwise return Duplicate Record
     * */
    private Optional<UsersCategory> validateDuplicateUsersCategory(String name){
        Optional<UsersCategory> usersCategoryOptional = userCategoryRepo.findByName(name);
        return usersCategoryOptional;
    }

    @Override
    /**
     * @Validate that no duplicate UsersCategory is allowed
     * @Validate that Users category exists otherwise return record not found
     * Create the Users definition and save
     * @return success message
     * * */
    public ApiResponse<String> addUsersCategory(UserCategoryRequest request) {
    Optional<UsersCategory> usersCategoryOptional = validateDuplicateUsersCategory(request.getName());

        if (!usersCategoryOptional.isEmpty()) {
            return new ApiResponse("Duplicate Record", AppStatus.FAILED.label,
                    HttpStatus.EXPECTATION_FAILED.value());
        }

    UsersCategory usersCategory = new UsersCategory();
    usersCategory.setName(request.getName());
    usersCategory.setDescription(request.getDescription());

    userCategoryRepo.save(usersCategory);

        return new ApiResponse("Record Added successfully", AppStatus.SUCCESS.label, HttpStatus.OK.value());
    }

    @Override
    public ApiResponse<UserCategoryResponse> getUsersTypeById(UUID usersCategoryId) {
        return null;
    }

    @Override
    public ApiResponse<UserCategoryResponse> getUsersTypeByName(String name) {
        return null;
    }


    /**
     * @validating UsersCategoryOptional by uuid
     * @Validate if the List of UsersCategory is empty otherwise return record not found
     * @return UsersCategoryOptional
     * * */
    private UsersCategory validateUsersCategory(UUID uuid){
        Optional<UsersCategory> usersTypeOptional = userCategoryRepo.findByUuid(uuid);
        if(usersTypeOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return usersTypeOptional.get();
    }


    @Override
    /**
     * @validating UsersCategoryOptional by uuid
     * @Validate if the List of UsersCategoryOptional is empty otherwise return record not found
     * Create the UsersCategory definition and update
     * @return a Success Message
     * * */
    public ApiResponse<String> updateUsersCategory(UUID usersCategoryId, UserCategoryRequest request) {
        UsersCategory userCategory = validateUsersCategory(usersCategoryId);

        if(request.getName() !=null){
            userCategory.setName(request.getName());
        }
        if(request.getDescription() !=null){
            userCategory.setDescription(request.getDescription());
        }

        userCategoryRepo.save(userCategory);

        return new ApiResponse<>(AppStatus.SUCCESS.label, "Record Updated Successfully");
    }



    @Override
    /**
     * @validate UsersCategory by uuid
     * @Validate if UsersCategory is empty otherwise return record not found
     * @Delete UsersCategory
     * @return a Success Message
     * * */
    public ApiResponse<String> deleteUsersCategory(UUID usersCategoryId) {

        UsersCategory usersCategory = validateUsersCategory(usersCategoryId);

        userCategoryRepo.delete(usersCategory);
        return new ApiResponse("Record Deleted Successfully", AppStatus.SUCCESS.label, HttpStatus.OK);
    }



}
