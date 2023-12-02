package Salvation.Clinic.controller.TransactionController;


import Salvation.Clinic.model.dto.request.TransactionRequest.DrugOrderItemsRequest;
import Salvation.Clinic.model.dto.request.TransactionRequest.OrderRequest;
import Salvation.Clinic.model.dto.request.TransactionRequest.PaymentRequest;
import Salvation.Clinic.model.dto.request.TransactionRequest.UpdateOrderRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.TransactionResponse.OrderResponse;
import Salvation.Clinic.model.dto.response.TransactionResponse.PaymentResponse;
import Salvation.Clinic.services.transactions.OrderService;
import Salvation.Clinic.services.transactions.PaymentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static Salvation.Clinic.EndPoints.EndpointParam.*;
import static Salvation.Clinic.EndPoints.TransactionEndPoints.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(transaction)
@RequiredArgsConstructor
public class TransactionController {
    private final OrderService orderService;
    private final PaymentService paymentService;


                                        // LIST OF TRANSACTIONS

    @GetMapping(FIND_ORDER)
    @ApiOperation(value = "Endpoint for finding the list of drugOrder ", response = OrderResponse.class, responseContainer = "List")
    public ApiResponse<List<OrderResponse>> findListOfDrugOrder(@RequestParam(value = PAGE, defaultValue = PAGE_DEFAULT) int page,
                                                                @RequestParam(value = SIZE, defaultValue = SIZE_DEFAULT) int size){
        return orderService.getListOfDrugOrder(page, size);
    }

    @GetMapping(FIND_PAYMENT)
    @ApiOperation(value = "Endpoint for finding the list of payment ", response = PaymentResponse.class, responseContainer = "List")
    public ApiResponse<List<PaymentResponse>> findListOfPayment(@RequestParam(value = PAGE, defaultValue = PAGE_DEFAULT) int page,
                                                                    @RequestParam(value = SIZE, defaultValue = SIZE_DEFAULT) int size){
        return paymentService.getListOfPayment(page, size);
    }


                                        // ADD TRANSACTION

    @PostMapping(ADD_ORDER)
    @ApiOperation(value = "Endpoint for adding drugOrder", response = String.class)
    public ApiResponse<String> addDrugOrder(@Valid @RequestBody OrderRequest request){
        return orderService.addDrugOrder(request);
    }

    @PostMapping(ADD_PAYMENT)
    @ApiOperation(value = "Endpoint for adding payment to database", response = String.class)
    public ApiResponse<String> addPayment(@Valid @RequestBody PaymentRequest request){
        return paymentService.addPayment(request);
    }


                                    // UPDATE TRANSACTION

    @PutMapping(UPDATE_ORDER)
    @ApiOperation(value = "Endpoint for updating drugOrder in the database", response = String.class)
    public ApiResponse<String> updateDrugOrder (@PathVariable(value = "uuid") UUID drugOrderItemUuid, @RequestBody UpdateOrderRequest request){
        return orderService.updateDrugOrder(drugOrderItemUuid, request);
    }


                                    // DELETE TRANSACTION

    @DeleteMapping(DELETE_ORDER)
    @ApiOperation(value = "Endpoint for deleting drugOrder", response = String.class)
    public ApiResponse<String> deleteDrugOrder(@PathVariable UUID drugOrderId){
        return orderService.deleteDrugOrder(drugOrderId);
    }


                                    // FIND PAYMENT BY DATE RANGE
    @GetMapping(FIND_LIST_OF_PAYMENT_BY_DATE_RANGE)
    @ApiOperation(value = "Endpoint for finding list of payment by date range", response = PaymentResponse.class, responseContainer = "List")
    public ApiResponse<List<PaymentResponse>> findPaymentByDateRange(String from, String to){
        return paymentService.findListOfPaymentByDateRange(from, to);
    }



}
