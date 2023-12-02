package Salvation.Clinic.services.treatment;

import Salvation.Clinic.model.dto.request.TransactionRequest.PaymentRequest;
import Salvation.Clinic.model.dto.request.TreatmentRequest.AdmissionRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.TransactionResponse.PaymentResponse;
import Salvation.Clinic.model.dto.response.TreatmentResponse.AdmissionResponse;

import java.util.List;
import java.util.UUID;

public interface AdmissionService {

    ApiResponse<List<AdmissionResponse>> getListOfAdmission(int page, int size);

    ApiResponse <String> addAdmission(AdmissionRequest request);

    ApiResponse <String> updateAdmission (UUID admissionUuid, AdmissionRequest request);

    ApiResponse <String> deleteAdmission (UUID admissionUuid);


}
