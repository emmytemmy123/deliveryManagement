package Salvation.Clinic.services.room;


import Salvation.Clinic.model.dto.request.RoomRequest.RoomRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.RoomResponse.RoomResponse;

import java.util.List;
import java.util.UUID;

public interface RoomService {

    ApiResponse <List<RoomResponse>> getListOfRoom (int page, int size);
    ApiResponse <String> addRoom (RoomRequest request);
    ApiResponse <String> updateRoom (UUID roomUuid, RoomRequest request);
    ApiResponse <String> deleteRoom (UUID roomUuid);



}
