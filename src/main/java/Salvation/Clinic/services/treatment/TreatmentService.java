package Salvation.Clinic.services.treatment;

import Salvation.Clinic.model.dto.request.TreatmentRequest.TreatmentRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.TreatmentResponse.TreatmentResponse;

import java.util.List;
import java.util.UUID;

public interface TreatmentService {

    ApiResponse<List<TreatmentResponse>> getListOfTreatment(int page, int size);

    ApiResponse <String> addTreatment(TreatmentRequest request);

    ApiResponse <String> updateTreatment (UUID treatmentUuid, TreatmentRequest request);

    ApiResponse <String> deleteTreatment (UUID treatmentUuid);

}
