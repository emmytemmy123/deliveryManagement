package Salvation.Clinic.services.drugs;


import Salvation.Clinic.model.dto.request.DrugRequest.DrugCategoryRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.DrugResponse.DrugCategoryResponse;
import io.swagger.annotations.Api;

import java.util.List;
import java.util.UUID;

public interface DrugCategoryService {

    ApiResponse <List<DrugCategoryResponse>> getListOfDrugCategory (int page, int size);
    ApiResponse <String> addDrugCategory (DrugCategoryRequest request);
    ApiResponse <String> updateDrugCategory (UUID drugCategoryUuid, DrugCategoryRequest request);
    ApiResponse <String> deleteDrugCategory (UUID drugCategoryUuid);


}
