package Salvation.Clinic.services.drugs;


import Salvation.Clinic.model.dto.request.DrugRequest.DrugRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.DrugResponse.DrugResponse;
import io.swagger.annotations.Api;

import java.util.List;
import java.util.UUID;

public interface DrugService {

    ApiResponse <List<DrugResponse>> getListOfDrug (int page, int size);
    ApiResponse <String> addDrug (DrugRequest request);
    ApiResponse <String> updateDrug (UUID drugUuid, DrugRequest request);
    ApiResponse <String> deleteDrug (UUID drugUuid);



}
