package delivery.management.services.transaction;


import delivery.management.model.dto.BaseDto;
import delivery.management.model.dto.request.transactionRequest.PaymentRequest;
import delivery.management.model.dto.request.userRequest.UsersRequest;
import delivery.management.model.dto.response.othersResponse.ApiResponse;
import delivery.management.model.dto.response.transactionResponse.DispatchResponse;
import delivery.management.model.dto.response.transactionResponse.PaymentResponse;
import delivery.management.model.entity.transaction.Payment;

import java.util.List;
import java.util.UUID;

public interface PaymentService {

    ApiResponse<List<PaymentResponse>, BaseDto> getListOfPayment(int page, int size);

    ApiResponse<String, BaseDto> addPayment(PaymentRequest request);

    ApiResponse<List<PaymentResponse>, BaseDto> getPaymentBySender(UUID paymentUuid);

    delivery.management.dto.ApiResponse<PaymentResponse> getPaymentByDeliveryNo (String deliveryNo);

    delivery.management.dto.ApiResponse<String> updatePayment(UUID paymentUuid, PaymentRequest request);


}
