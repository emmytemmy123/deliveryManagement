package delivery.management.services.Transportation;


import delivery.management.dto.ApiResponse;
import delivery.management.model.dto.request.transportationRequest.VehicleCategoryRequest;
import delivery.management.model.dto.response.transportationResponse.VehicleCategoryResponse;

import java.util.List;
import java.util.UUID;

public interface VehicleCategoryService {

    ApiResponse<List<VehicleCategoryResponse>> getListOfVehicleCategory(int page, int size);

    ApiResponse<String> addVehicleCategory(VehicleCategoryRequest request);

    ApiResponse<VehicleCategoryResponse> getVehicleCategoryById(UUID vehicleCategoryId);

    ApiResponse<String> updateVehicleCategory( UUID vehicleCategoryId, VehicleCategoryRequest request);

    ApiResponse<String> deleteVehicleCategory(UUID vehicleCategoryId);


}
