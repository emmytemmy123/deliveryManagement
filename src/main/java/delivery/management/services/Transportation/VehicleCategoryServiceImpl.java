package delivery.management.services.Transportation;


import delivery.management.dto.ApiResponse;
import delivery.management.exception.RecordNotFoundException;
import delivery.management.mapper.Mapper;
import delivery.management.model.dto.enums.AppStatus;
import delivery.management.model.dto.request.transportationRequest.VehicleCategoryRequest;
import delivery.management.model.dto.response.transportationResponse.VehicleCategoryResponse;
import delivery.management.model.entity.transportation.VehicleCategory;
import delivery.management.repo.transportation.VehicleCategoryRepository;
import delivery.management.repo.user.UsersRepository;
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
public class VehicleCategoryServiceImpl implements VehicleCategoryService {

    private final VehicleCategoryRepository vehicleCategoryRepository;
    private final UsersRepository usersRepository;


    @Override
    /**
     * @Validate and Find the list of all vehicleCategory
     * @Validate if the List of vehicleCategory is empty otherwise return record not found
     * @return the list of vehicleCategory and a Success Message
     * * */
    public ApiResponse<List<VehicleCategoryResponse>> getListOfVehicleCategory(int page, int size) {

        List<VehicleCategory> vehicleCategoryList = vehicleCategoryRepository.findAll(PageRequest.of(page,size)).toList();
        if(vehicleCategoryList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse(AppStatus.SUCCESS.label,
                Mapper.convertList(vehicleCategoryList, VehicleCategoryResponse.class));

    }


    /**
     * @Validating existingVehicleCategoryOptional by name
     * @Validate if the List of existingVehicleCategoryOptional is empty otherwise return Duplicate Record
     * */
    private Optional<VehicleCategory> validateDuplicateVehicleCategory(String name) {
        Optional<VehicleCategory> existingVehicleCategoryOptional = vehicleCategoryRepository.findByName(name);
        return existingVehicleCategoryOptional;

    }



    @Override
    /**
     * @Validate that no duplicate vehicleCategory is allowed
     * @Validate that vehicleCategory exists otherwise return record not found
     * Create the vehicleCategory definition and save
     * @return success message
     * * */
    public ApiResponse<String> addVehicleCategory(VehicleCategoryRequest request) {

        Optional<VehicleCategory> vehicleCategoryOptional = validateDuplicateVehicleCategory(request.getName());


        if (!vehicleCategoryOptional.isEmpty()) {
            return new ApiResponse("Duplicate Record", AppStatus.FAILED.label,
                    HttpStatus.BAD_REQUEST.value());
        }

        VehicleCategory vehicleCategory = new VehicleCategory();

        vehicleCategory.setName(request.getName());
        vehicleCategory.setDescription(request.getDescription());

        vehicleCategoryRepository.save(vehicleCategory);

        return new ApiResponse("Record Added successfully", AppStatus.SUCCESS.label,
                HttpStatus.OK.value());


    }



    @Override
    /**
     * @Validating and Finding the list of vehicleCategoryOptional by uuid
     * @Validate if the List of vehicleCategoryOptional is empty otherwise return record not found
     * Create the vehicleCategory definition and get the vehicleCategoryOptional by uuid
     * @return the list of vehicleCategory and a Success Message
     * * */
    public ApiResponse<VehicleCategoryResponse> getVehicleCategoryById(UUID vehicleCategoryId) {

        Optional<VehicleCategory> vehicleCategoryOptional = vehicleCategoryRepository.findByUuid(vehicleCategoryId);

        if(vehicleCategoryOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        VehicleCategory vehicleCategory = vehicleCategoryOptional.get();

        return new ApiResponse<VehicleCategoryResponse>(AppStatus.SUCCESS.label,
                Mapper.convertObject(vehicleCategory,VehicleCategoryResponse.class));


    }


    /**
     * @validating vehicleCategoryOptional by uuid
     * @Validate if the List of vehicleCategory is empty otherwise return record not found
     * @return vehicleCategoryOptional
     * * */
    private VehicleCategory validateVehicleCategory(UUID uuid){
        Optional<VehicleCategory> vehicleCategoryOptional = vehicleCategoryRepository.findByUuid(uuid);
        if(vehicleCategoryOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return vehicleCategoryOptional.get();
    }



    @Override
    /**
     * @validating producCategoryOptional by uuid
     * @Validate if the List of productCategoryOptional is empty otherwise return record not found
     * Create the productCategory definition and update
     * @return a Success Message
     * * */
    public ApiResponse<String> updateVehicleCategory(UUID vehicleCategoryId, VehicleCategoryRequest request) {

        VehicleCategory vehicleCategory = validateVehicleCategory(vehicleCategoryId);

        vehicleCategory.setName(request.getName());
        vehicleCategory.setDescription(request.getDescription());

        vehicleCategoryRepository.save(vehicleCategory);

        return new ApiResponse(AppStatus.SUCCESS.label, "Record Updated Successfully");

    }


    @Override
    /**
     * @validate vehicleCategory by uuid
     * @Validate if vehicleCategory is empty otherwise return record not found
     * @Delete vehicleCategory
     * @return a Success Message
     * * */
    public ApiResponse<String> deleteVehicleCategory(UUID vehicleCategoryId) {

        VehicleCategory vehicleCategory = validateVehicleCategory(vehicleCategoryId);

        vehicleCategoryRepository.delete(vehicleCategory);

        return new ApiResponse(AppStatus.SUCCESS.label, "Record Deleted successfully");

    }




}
