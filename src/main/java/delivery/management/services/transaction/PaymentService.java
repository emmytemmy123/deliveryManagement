package delivery.management.services.transaction;

import delivery.management.model.dto.request.transactionRequest.PaymentRequest;
import delivery.management.model.dto.response.othersResponse.ApiResponse;
import delivery.management.model.dto.response.transactionResponse.PaymentResponse;

import java.util.List;
import java.util.UUID;


public interface PaymentService {

    ApiResponse<List<PaymentResponse>> getListOfPayment(int page, int size);

    ApiResponse<List<PaymentResponse>> findListOfPaymentByDateRange( String from, String to);

    ApiResponse<String> addPayment(PaymentRequest request);

//    ApiResponse<PaymentResponse> getPaymentById(UUID paymentId);

    ApiResponse<List<PaymentResponse>> getPaymentByDeliveryId(UUID deliveryId);

    ApiResponse<List<PaymentResponse>> findPaymentByDate(String dateCreated);

    ApiResponse<List<PaymentResponse>> findPaymentByCustomer(UUID customerId);




}
