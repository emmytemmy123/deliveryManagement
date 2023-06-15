package delivery.management.services.user;


import delivery.management.dto.ApiResponse;
import delivery.management.exception.RecordNotFoundException;
import delivery.management.mapper.Mapper;
import delivery.management.model.dto.enums.AppStatus;
import delivery.management.model.dto.request.userRequest.UserTypeRequest;
import delivery.management.model.dto.response.UserTypeResponse;
import delivery.management.model.entity.user.UsersType;
import delivery.management.repo.user.UsersRepository;
import delivery.management.repo.user.UsersTypeRepository;
import delivery.management.utills.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersTypeServiceImpl implements UserTypeService {

    private  final UsersTypeRepository usersTypeRepository;
    private final UsersRepository usersRepository;

    @Override
    /**
     * @Validate and Find the list of all UsersCategory
     * @Validate if the List of UsersCategory is empty otherwise return record not found*
     * @return the list of UsersCategory and a Success Message* *
     * * */
    public ApiResponse<List<UserTypeResponse>> getListOfUsersCategory(int page, int size) {

        List<UsersType> usersTypeList = usersTypeRepository.findAll(PageRequest.of(page,size)).toList();
        if(usersTypeList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label,
                Mapper.convertList(usersTypeList, UserTypeResponse.class));
    }



    @Override
    /**
     * @Validate that no duplicate UsersCategory is allowed
     * @Validate that Users category exists otherwise return record not found
     * Create the Users definition and save
     * @return success message
     * * */
    public ApiResponse<String> addUsersCategory(UserTypeRequest request) {

        Optional<UsersType> usersTypeOptional = validateDuplicateUsersCategory(request.getName());


        if (!usersTypeOptional.isEmpty()) {
            return new ApiResponse("Duplicate Record", AppStatus.FAILED.label,
                    HttpStatus.EXPECTATION_FAILED.value());
        }

        UsersType usersType = new UsersType();

        usersType.setName(request.getName());
        usersType.setDescription(request.getDescription());

        usersTypeRepository.save(usersType);

        return new ApiResponse("Record Added successfully", AppStatus.SUCCESS.label,
                HttpStatus.OK.value());
    }

    /**
     * @Validating existingUsersCategoryOptional by name
     * @Validate if the List of existingUsersCategoryOptional is empty otherwise return Duplicate Record
     * */
    private Optional<UsersType> validateDuplicateUsersCategory(String name) {
        Optional<UsersType> existingUsersCategoryOptional = usersTypeRepository.findByName(name);
        return existingUsersCategoryOptional;

    }


    /**
     * Set and get the UsersCategory parameters
     */
    private UsersType getUsersCategoryFromRequest(UserTypeRequest request) {
        UsersType usersType = new UsersType();

        usersType.setName(request.getName());
        usersType.setDescription(request.getDescription());

        return usersType;
    }

    /**
     * @validating UsersCategoryOptional by uuid
     * @Validate if the List of UsersCategory is empty otherwise return record not found
     * @return UsersCategoryOptional
     * * */
    private UsersType validateUsersCategory(UUID uuid){
        Optional<UsersType> usersTypeOptional = usersTypeRepository.findByUuid(uuid);
        if(usersTypeOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return usersTypeOptional.get();
    }


    @Override
    /**
     * @Validating and Finding the list of productCategoryOptional by uuid
     * @Validate if the List of productCategoryOptional is empty otherwise return record not found
     * Create the productCategory definition and get the productCategoryOptional by uuid
     * @return the list of productCategory and a Success Message
     * * */
    public ApiResponse<UserTypeResponse> getUsersTypeById(UUID usersCategoryId) {

        Optional<UsersType> usersTypeOptional = usersTypeRepository.findByUuid(usersCategoryId);

        if(usersTypeOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        UsersType usersType = usersTypeOptional.get();

        return new ApiResponse<UserTypeResponse>(AppStatus.SUCCESS.label,
                Mapper.convertObject(usersType,UserTypeResponse.class));
    }

    @Override
    public ApiResponse<UserTypeResponse> getUsersTypeByName(String name) {

        Optional<UsersType> usersTypeOptional = usersTypeRepository.findByName(name);

        if(usersTypeOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label,
                Mapper.convertObject(usersTypeOptional, UserTypeResponse.class));

    }


    @Override
    /**
     * @validating UsersCategoryOptional by uuid
     * @Validate if the List of UsersCategoryOptional is empty otherwise return record not found
     * Create the UsersCategory definition and update
     * @return a Success Message
     * * */
    public ApiResponse<String> updateUsersCategory(UUID usersCategoryId,UserTypeRequest request) {

        UsersType usersType = validateUsersCategory(usersCategoryId);

        usersType.setName(request.getName());
        usersType.setDescription(request.getDescription());

        usersTypeRepository.save(usersType);

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

        UsersType usersType = validateUsersCategory(usersCategoryId);

        usersTypeRepository.delete(usersType);

        return new ApiResponse("Record Deleted successfully", AppStatus.SUCCESS.label,
                HttpStatus.OK.value());
    }



}
