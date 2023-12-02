package Salvation.Clinic.services.drugs;


import Salvation.Clinic.mapper.Mapper;
import Salvation.Clinic.model.dto.enums.AppStatus;
import Salvation.Clinic.model.dto.request.DrugRequest.DrugCategoryRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.DrugResponse.DrugCategoryResponse;
import Salvation.Clinic.model.dto.response.exception.RecordNotFoundException;
import Salvation.Clinic.model.entity.drug.DrugCategory;
import Salvation.Clinic.model.entity.room.RoomCategory;
import Salvation.Clinic.model.entity.users.Users;
import Salvation.Clinic.repo.drugRepo.DrugCategoryRepo;
import Salvation.Clinic.repo.userRepo.UserRepo;
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
public class DrugCategoryServiceImpl implements DrugCategoryService {

    private final DrugCategoryRepo drugCategoryRepo;
    private final UserRepo userRepo;


    @Override
    /**
     * @Validate and Find the list of all drugCategory
     * @Validate if the List of drugCategory is empty otherwise return record not found*
     * @return the list of drugCategory and a Success Message* *
     * * */
    public ApiResponse<List<DrugCategoryResponse>> getListOfDrugCategory(int page, int size) {
    List<DrugCategory> drugCategoryList = drugCategoryRepo.findAll(PageRequest.of(page, size)).toList();
    if(drugCategoryList.isEmpty()){
        throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
    }

        return new ApiResponse (AppStatus.SUCCESS.label,
                Mapper.convertList(drugCategoryList, DrugCategoryResponse.class));

    }

    /**
     * @Validating existingDrugCategoryOptional by name
     * @Validate if the List of existingDrugCategoryOptional is empty otherwise return Duplicate Record
     * */
    private Optional<DrugCategory> validateDuplicateDrugCategory(String name){
        Optional<DrugCategory> drugCategoryOptional = drugCategoryRepo.findCategoryByName(name);
        return drugCategoryOptional;
    }

    @Override
    /**
     * @Validate that no duplicate productCategory is allowed
     * @Validate that product category exists otherwise return record not found
     * Create the product definition and save
     * @return success message* *
     * * */
    public ApiResponse<String> addDrugCategory(DrugCategoryRequest request) {
        validateDuplicateDrugCategory(request.getName());

        Users existingUser = userRepo.findByUuid(request.getCreatedById()).orElseThrow( ()->
                new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        DrugCategory drugCategory = new DrugCategory();

        drugCategory.setName(request.getName());
        drugCategory.setDescription(request.getDescription());
        drugCategory.setPostedBy(existingUser.getName());

        drugCategoryRepo.save(drugCategory);
        return new ApiResponse(AppStatus.SUCCESS.label, "Record Added Successfully");
    }


    /**
     * @validating drugCategoryOptional by uuid
     * @Validate if the List of drugCategoryOptional is empty otherwise return record not found
     * @return drugCategoryOptional
     * * */
    private DrugCategory validateDrugCategoryByUuid(UUID uuid){
        Optional<DrugCategory> drugCategoryOptional = drugCategoryRepo.deleteByUuid(uuid);
        if(drugCategoryOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return drugCategoryOptional.get();
    }


    @Override
    /**
     * @validating drugCategory by uuid
     * @Validate if the List of drugCategory is empty otherwise return record not found
     * Create the drugCategory definition and update
     * @return a Success Message
     * * */
    public ApiResponse<String> updateDrugCategory(UUID drugCategoryUuid, DrugCategoryRequest request) {
    DrugCategory drugCategory = validateDrugCategoryByUuid(drugCategoryUuid);

    if(request.getName() != null){
        drugCategory.setName(request.getName());
    }
    if(request.getDescription() != null){
        drugCategory.setDescription(request.getDescription());
    }

    drugCategoryRepo.save(drugCategory);

    return new ApiResponse<>(AppStatus.SUCCESS.label, "Record Updated Successfully");

    }

    @Override
    /**
     * @validate DrugCategory by uuid
     * @Validate if DrugCategory is empty otherwise return record not found
     * @Delete DrugCategory
     * @return a Success Message
     * * */
    public ApiResponse<String> deleteDrugCategory(UUID drugCategoryUuid) {
    DrugCategory drugCategory = validateDrugCategoryByUuid(drugCategoryUuid);
    drugCategoryRepo.delete(drugCategory);
    return new ApiResponse<>(AppStatus.SUCCESS.label, "Record Deleted Successfully");
    }


}
