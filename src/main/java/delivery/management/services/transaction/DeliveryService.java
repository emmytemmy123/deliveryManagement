package delivery.management.services.transaction;


import delivery.management.model.dto.request.transactionRequest.DeliveryRequest;
import delivery.management.model.dto.response.othersResponse.ApiResponse;
import delivery.management.model.dto.response.transactionResponse.DeliveryResponse;

import java.util.List;
import java.util.UUID;


public interface DeliveryService {

    ApiResponse<List<DeliveryResponse>> getListOfDelivery(int page, int size);

    ApiResponse<DeliveryResponse> getDeliveryById(UUID deliveryId);

    ApiResponse<String> addDelivery(DeliveryRequest request);

    ApiResponse<String> updateDelivery(UUID productItemUuid, DeliveryRequest request);

    ApiResponse<List<DeliveryResponse>> getDeliveryBySender(UUID deliveryUuid);

    ApiResponse<List<DeliveryResponse>> findDeliveryByDate(String dateCreated);



}
