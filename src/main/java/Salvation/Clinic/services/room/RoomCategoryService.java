package Salvation.Clinic.services.room;


import Salvation.Clinic.model.dto.request.RoomRequest.RoomCategoryRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.RoomResponse.RoomCategoryResponse;

import java.util.List;
import java.util.UUID;

public interface RoomCategoryService  {

    ApiResponse<List<RoomCategoryResponse>> getListOfRoomCategory(int page, int size);

    ApiResponse<String> addRoomCategory(RoomCategoryRequest request);

    ApiResponse<String> updateRoomCategory( UUID roomCategoryId, RoomCategoryRequest request);

    ApiResponse<String> deleteRoomCategory(UUID roomCategoryId);


}
