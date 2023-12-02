package Salvation.Clinic.controller.RoomController;


import Salvation.Clinic.model.dto.request.RoomRequest.RoomCategoryRequest;
import Salvation.Clinic.model.dto.request.RoomRequest.RoomRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.RoomResponse.RoomCategoryResponse;
import Salvation.Clinic.model.dto.response.RoomResponse.RoomResponse;
import Salvation.Clinic.services.room.RoomCategoryService;
import Salvation.Clinic.services.room.RoomService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static Salvation.Clinic.EndPoints.EndpointParam.*;
import static Salvation.Clinic.EndPoints.RoomEndPoints.*;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(room)
@RequiredArgsConstructor
@RestController
public class RoomController {
    private final RoomService roomService;
    private final RoomCategoryService roomCategoryService;


                                        // FIND LIST OF ROOMS

    @GetMapping(FIND_ROOM)
    @ApiOperation(value = "Endpoint for finding the list of rooms", response = RoomResponse.class, responseContainer = "List")
    public ApiResponse<List<RoomResponse>> findListOfRooms(@RequestParam(value =PAGE, defaultValue =PAGE_DEFAULT ) int page,
                                                           @RequestParam(value = SIZE, defaultValue = SIZE_DEFAULT) int size){
        return roomService.getListOfRoom(page, size);
    }

    @GetMapping(FIND_ROOM_CATEGORY)
    @ApiOperation(value = "Endpoint for finding the list of roomCategory", response = RoomCategoryResponse.class, responseContainer = "List")
    public ApiResponse<List<RoomCategoryResponse>> findListOfRoomCategory(@RequestParam(value =PAGE, defaultValue =PAGE_DEFAULT ) int page,
                                                           @RequestParam(value = SIZE, defaultValue = SIZE_DEFAULT) int size){
        return roomCategoryService.getListOfRoomCategory(page, size);
    }

                                            // ADD ROOM

    @PostMapping(ADD_ROOM)
    @ApiOperation(value = "Endpoint for adding room to database", response = String.class)
    public ApiResponse<String> addRoom(@Valid @RequestBody RoomRequest request){
        return roomService.addRoom(request);
    }

    @PostMapping(ADD_ROOM_CATEGORY)
    @ApiOperation(value = "Endpoint for adding roomCategory to database", response = String.class)
    public ApiResponse<String> addRoomCategory(@Valid @RequestBody RoomCategoryRequest request){
        return roomCategoryService.addRoomCategory(request);
    }


                                        // UPDATE ROOM

    @PutMapping(UPDATE_ROOM)
    @ApiOperation(value = "Endpoint for updating room in the database", response = String.class)
    public ApiResponse<String> updateRoom(@PathVariable(value = "uuid") UUID roomUuid, @RequestBody RoomRequest request){
        return roomService.updateRoom(roomUuid, request);
    }

    @PutMapping(UPDATE_ROOM_CATEGORY)
    @ApiOperation(value = "Endpoint for updating roomCategory in the database", response = String.class)
    public ApiResponse<String> updateRoomCategory(@PathVariable(value = "uuid") UUID roomCategoryUuid, @RequestBody RoomCategoryRequest request){
        return roomCategoryService.updateRoomCategory(roomCategoryUuid, request);
    }

                                        // DELETE ROOM

    @DeleteMapping(DELETE_ROOM)
    @ApiOperation(value = "Endpoint for deleting room in the database", response = String.class)
    public ApiResponse<String> deleteRoom(@PathVariable(value = "uuid") UUID roomUuid){
        return roomService.deleteRoom(roomUuid);
    }

    @DeleteMapping(DELETE_ROOM_CATEGORY)
    @ApiOperation(value = "Endpoint for deleting roomCategory in the database", response = String.class)
    public ApiResponse<String> deleteRoomCategory(@PathVariable(value = "uuid") UUID roomCategoryUuid){
        return roomCategoryService.deleteRoomCategory(roomCategoryUuid);
    }




}
