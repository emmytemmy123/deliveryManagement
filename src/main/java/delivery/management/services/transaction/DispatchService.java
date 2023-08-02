package delivery.management.services.transaction;


import delivery.management.model.dto.BaseDto;
import delivery.management.model.dto.request.transactionRequest.DispatchDriverRequest;
import delivery.management.model.dto.response.othersResponse.ApiResponse;
import delivery.management.model.dto.response.transactionResponse.DispatchResponse;

import java.util.List;
import java.util.UUID;

public interface DispatchService {

    ApiResponse<List<DispatchResponse>, BaseDto> getListOfDispatch(int page, int size);

    ApiResponse<String, BaseDto> addDispatch(DispatchDriverRequest request);

    delivery.management.dto.ApiResponse<DispatchResponse> getDispatchByDeliveryNo (String deliveryNo);

    ApiResponse<List<DispatchResponse>, BaseDto> getListOfDispatchByName(String dispatchName);

    delivery.management.dto.ApiResponse<DispatchResponse> getDispatchByEmail (String email);




}
