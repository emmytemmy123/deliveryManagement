package Salvation.Clinic.services.transactions;

import Salvation.Clinic.model.dto.request.TransactionRequest.PaymentRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.TransactionResponse.PaymentResponse;

import java.util.List;
import java.util.UUID;

public interface PaymentService {

    ApiResponse<List<PaymentResponse>> getListOfPayment(int page, int size);

    ApiResponse <String> addPayment(PaymentRequest request);

    ApiResponse <String> updatePayment (UUID paymentUuid, PaymentRequest request);

    ApiResponse <String> deletePayment (UUID paymentUuid);

    ApiResponse<List<PaymentResponse>> findListOfPaymentByDateRange( String from, String to);




}
