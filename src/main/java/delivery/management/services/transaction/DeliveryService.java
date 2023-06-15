package delivery.management.services.transaction;


import delivery.management.model.dto.BaseDto;
import delivery.management.model.dto.request.transactionRequest.DeliveryRequest;
import delivery.management.model.dto.response.othersResponse.ApiResponse;
import delivery.management.model.dto.response.transactionResponse.DeliveryResponse;

import java.util.List;
import java.util.UUID;


public interface DeliveryService {

    ApiResponse<List<DeliveryResponse>, BaseDto> getListOfDelivery(int page, int size);

    ApiResponse<DeliveryResponse, BaseDto> getDeliveryById(UUID deliveryId);

    ApiResponse<String, BaseDto> addDelivery(DeliveryRequest request);

    ApiResponse<String, BaseDto> updateDelivery(UUID productItemUuid, DeliveryRequest request);

    ApiResponse<List<DeliveryResponse>, BaseDto> getDeliveryBySender(UUID deliveryUuid);

    ApiResponse<List<DeliveryResponse>, BaseDto> findDeliveryByDate(String dateCreated);



}
