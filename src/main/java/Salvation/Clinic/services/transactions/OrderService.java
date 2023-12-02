package Salvation.Clinic.services.transactions;

import Salvation.Clinic.model.dto.request.TransactionRequest.DrugOrderItemsRequest;
import Salvation.Clinic.model.dto.request.TransactionRequest.OrderRequest;
import Salvation.Clinic.model.dto.request.TransactionRequest.UpdateOrderRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.TransactionResponse.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    ApiResponse <List<OrderResponse>> getListOfDrugOrder(int page, int size);

    ApiResponse <String> addDrugOrder(OrderRequest request);

    ApiResponse <String> updateDrugOrder (UUID orderItemUuid, UpdateOrderRequest request);

    ApiResponse <String> deleteDrugOrder (UUID drugOrderUuid);



}
