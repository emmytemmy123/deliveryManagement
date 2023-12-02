package Salvation.Clinic.services.room;


import Salvation.Clinic.mapper.Mapper;
import Salvation.Clinic.model.dto.enums.AppStatus;
import Salvation.Clinic.model.dto.request.RoomRequest.RoomCategoryRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.RoomResponse.RoomCategoryResponse;
import Salvation.Clinic.model.dto.response.exception.RecordNotFoundException;
import Salvation.Clinic.model.entity.room.RoomCategory;
import Salvation.Clinic.model.entity.users.UsersCategory;
import Salvation.Clinic.repo.roomRepo.RoomCategoryRepo;
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
public class RoomCategoryServiceImpl implements RoomCategoryService {

    private final RoomCategoryRepo roomCategoryRepo;
    private final UserRepo userRepo;


    @Override
    /**
     * @Validate and Find the list of all RoomCategory
     * @Validate if the List of RoomCategory is empty otherwise return record not found
     * @return the list of RoomCategory and a Success Message
     * * */
    public ApiResponse<List<RoomCategoryResponse>> getListOfRoomCategory(int page, int size) {
        List<RoomCategory> roomCategoryList = roomCategoryRepo.findAll(PageRequest.of(page, size)).toList();
        if(roomCategoryList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse(AppStatus.SUCCESS.label, Mapper.convertList(roomCategoryList, RoomCategory.class));

    }

    /**
     * @Validating existingRoomCategoryOptional by name
     * @Validate if the List of existingRoomCategoryOptional is empty otherwise return Duplicate Record
     * */
    private Optional<RoomCategory> validateDuplicateRoomCategory(String name){
        Optional<RoomCategory> roomCategoryOptional = roomCategoryRepo.findByRoomName(name);
        return roomCategoryOptional;
    }

    @Override
    /**
     * @Validate that no duplicate RoomCategory is allowed
     * @Validate that RoomCategory exists otherwise return record not found
     * Create the RoomCategory definition and save
     * @return success message
     * * */
    public ApiResponse<String> addRoomCategory(RoomCategoryRequest request) {
        Optional<RoomCategory> roomCategoryOptional = validateDuplicateRoomCategory(request.getName());

        if (!roomCategoryOptional.isEmpty()) {
            return new ApiResponse("Duplicate Record", AppStatus.FAILED.label,
                    HttpStatus.EXPECTATION_FAILED.value());
        }

        RoomCategory roomCategory = new RoomCategory();
        roomCategory.setName(request.getName());
        roomCategory.setDescription(request.getDescription());

        roomCategoryRepo.save(roomCategory);

        return new ApiResponse("Record Added successfully", AppStatus.SUCCESS.label, HttpStatus.OK.value());

    }


    /**
     * @validating RoomCategoryOptional by uuid
     * @Validate if the List of RoomCategory is empty otherwise return record not found
     * @return RoomCategoryOptional
     * * */
    private RoomCategory validateRoomCategory(UUID uuid){
        Optional<RoomCategory> roomCategoryOptional = roomCategoryRepo.findByUuid(uuid);
        if(roomCategoryOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return roomCategoryOptional.get();
    }


    @Override
    /**
     * @validating RoomCategoryOptional by uuid
     * @Validate if the List of RoomCategoryOptional is empty otherwise return record not found
     * Create the RoomCategory definition and update
     * @return a Success Message
     * * */
    public ApiResponse<String> updateRoomCategory(UUID roomCategoryId, RoomCategoryRequest request) {
        RoomCategory roomCategory = validateRoomCategory(roomCategoryId);

        if(request.getName() !=null){
            roomCategory.setName(request.getName());
        }
        if(request.getDescription() !=null){
            roomCategory.setDescription(request.getDescription());
        }

        roomCategoryRepo.save(roomCategory);

        return new ApiResponse<>(AppStatus.SUCCESS.label, "Record Updated Successfully");
    }


    @Override
    /**
     * @validate RoomCategory by uuid
     * @Validate if RoomCategory is empty otherwise return record not found
     * @Delete RoomCategory
     * @return a Success Message
     * * */
    public ApiResponse<String> deleteRoomCategory(UUID roomCategoryId) {
        RoomCategory roomCategory = validateRoomCategory(roomCategoryId);

        roomCategoryRepo.delete(roomCategory);
        return new ApiResponse("Record Deleted Successfully", AppStatus.SUCCESS.label, HttpStatus.OK);
    }



}
