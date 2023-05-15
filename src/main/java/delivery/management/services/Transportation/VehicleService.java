package delivery.management.services.Transportation;


import delivery.management.dto.ApiResponse;
import delivery.management.model.dto.request.transportationRequest.VehicleRequest;
import delivery.management.model.dto.response.transportationResponse.VehicleResponse;

import java.util.List;
import java.util.UUID;

public interface VehicleService {

    ApiResponse<List<VehicleResponse>> getListOfVehicle(int page, int size);

    ApiResponse<String> addVehicle(VehicleRequest request);

    ApiResponse<VehicleResponse> getVehicleById(UUID vehicleCategoryId);

    ApiResponse<String> updateVehicle( UUID vehicleId, VehicleRequest request);

    ApiResponse<String> deleteVehicle(UUID vehicleId);


}
