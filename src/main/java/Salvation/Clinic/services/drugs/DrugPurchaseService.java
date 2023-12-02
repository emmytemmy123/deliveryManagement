package Salvation.Clinic.services.drugs;


import Salvation.Clinic.model.dto.request.DrugRequest.DrugPurchaseRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.DrugResponse.DrugPurchaseResponse;
import Salvation.Clinic.model.entity.drug.DrugPurchase;

import java.util.List;
import java.util.UUID;

public interface DrugPurchaseService {

    ApiResponse <List<DrugPurchaseResponse>> getListOfDrugPurchase(int page, int size);
    ApiResponse <String> addDrugCategory (DrugPurchaseRequest request);
    ApiResponse <String> updateDrugPurchase (UUID drugPurchaseUuid, DrugPurchaseRequest request);
    ApiResponse <String> deleteDrugPurchase (UUID drugPurchaseUuid);



}
