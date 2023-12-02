package Salvation.Clinic.services.room;


import Salvation.Clinic.mapper.Mapper;
import Salvation.Clinic.model.dto.enums.AppStatus;
import Salvation.Clinic.model.dto.request.RoomRequest.RoomRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.RoomResponse.RoomResponse;
import Salvation.Clinic.model.dto.response.exception.RecordNotFoundException;
import Salvation.Clinic.model.entity.room.Room;
import Salvation.Clinic.model.entity.room.RoomCategory;
import Salvation.Clinic.model.entity.users.Users;
import Salvation.Clinic.repo.roomRepo.RoomCategoryRepo;
import Salvation.Clinic.repo.roomRepo.RoomRepo;
import Salvation.Clinic.repo.userRepo.UserRepo;
import Salvation.Clinic.utills.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{

    private final RoomRepo roomRepo;
    private final UserRepo userRepo;
    private final RoomCategoryRepo roomCategoryRepo;



    @Override
    /**
     * @Finding the list of all rooms
     * @Validate if the List of rooms is empty otherwise return record not found
     * @return the list of rooms and a Success Message* *
     * * */
    public ApiResponse<List<RoomResponse>> getListOfRoom(int page, int size) {
        List<Room> roomList = roomRepo.findAll(PageRequest.of(page, size)).toList();
        if(roomList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return new ApiResponse<>(AppStatus.SUCCESS.label, Mapper.convertList(roomList, RoomResponse.class));
    }


//    public void validateDuplicateRoom(String bedNo){
//        Optional<Room> roomOptional = roomRepo.findByBedNo(bedNo);
//        if(roomOptional.isPresent())
//            throw new RecordNotFoundException("Duplicate bedNo");
//    }


    @Override
    /**
     * @Validate that no duplicate rooms are allowed
     * @Validate that user creating the rooms exists, otherwise return user not found
     * Create the rooms definition and save
     * @return success message
     * * */
    public ApiResponse<String> addRoom(RoomRequest request) {

        Users existingUsers = userRepo.findByUuid(request.getCreatedById())
                .orElseThrow( () -> new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        RoomCategory existingRoomCategory = roomCategoryRepo.findByUuid(request.getRoomCategoryId())
                .orElseThrow( () -> new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        Room room = new Room();
        room.setRoomNo(request.getRoomNo());
        room.setCost(request.getCost());
        room.setNoOfBed(request.getNoOfBed());
        room.setStatus("available");
        room.setRoomType(existingRoomCategory.getName());
        room.setCreatedBy(existingUsers.getName());
        room.setRoomCategory(existingRoomCategory);

        roomRepo.save(room);
        return new ApiResponse<>(AppStatus.SUCCESS.label, "Record added Successfully");
    }


    public Room validateRoomById(UUID uuid){
        Optional<Room> roomOptional = roomRepo.deleteByUuid(uuid);
        if(roomOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return roomOptional.get();
    }


    @Override
    /**
     * @validating roomOptional by uuid
     * @Validate if the List of roomOptional is empty otherwise return record not found
     * Create the roomOptional definition and update
     * @return a Success Message
     * * */
    public ApiResponse<String> updateRoom(UUID roomUuid, RoomRequest request) {
        Room room = validateRoomById(roomUuid);

        if(request.getRoomNo() != null){
            request.setRoomNo(request.getRoomNo());
        }
        if(request.getCost() != null){
            request.setCost(request.getCost());
        }

        roomRepo.save(room);
        return new ApiResponse<>(AppStatus.SUCCESS.label, "Record updated Successfully");
    }

    @Override
    /**
     * @validating room by uuid
     * @Validate if room is empty otherwise return record not found
     * @Delete room
     * @return a Success Message
     * * */
    public ApiResponse<String> deleteRoom(UUID roomUuid) {
        Room room = validateRoomById(roomUuid);
        roomRepo.delete(room);
        return new ApiResponse<>(AppStatus.SUCCESS.label, "Record Deleted Successfully");

    }


}
