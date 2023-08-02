package delivery.management.controller.transactionControllers;


import delivery.management.model.dto.BaseDto;
import delivery.management.model.dto.request.transactionRequest.DeliveryRequest;
import delivery.management.model.dto.request.transactionRequest.DispatchDriverRequest;
import delivery.management.model.dto.request.transactionRequest.PaymentRequest;
import delivery.management.model.dto.request.userRequest.UsersRequest;
import delivery.management.model.dto.response.othersResponse.ApiResponse;
import delivery.management.model.dto.response.transactionResponse.DeliveryResponse;
import delivery.management.model.dto.response.transactionResponse.DispatchResponse;
import delivery.management.model.dto.response.transactionResponse.PaymentResponse;
import delivery.management.services.transaction.DeliveryService;
import delivery.management.services.transaction.DispatchService;
import delivery.management.services.transaction.PaymentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import delivery.management.utills.EndpointParam;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static delivery.management.utills.EndPoints.TransactionEndPoints.*;
import static delivery.management.utills.EndPoints.UsersEndPoints.UPDATE_USERS;

@CrossOrigin
@RestController
@RequestMapping(transaction)
@RequiredArgsConstructor
public class TransactionController {

    private final DeliveryService deliveryService;
    private final DispatchService dispatchService;
    private final PaymentService paymentService;


                                //FIND_LISTS_OF_TRANSACTIONS

    @GetMapping(FIND_DELIVERY)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for retrieving lists of delivery", response = DeliveryResponse.class, responseContainer = "List")
    public ApiResponse<List<DeliveryResponse>, BaseDto> getListOfDelivery(@RequestParam(value = EndpointParam.PAGE, defaultValue = EndpointParam.PAGE_DEFAULT) int page,
                                                                          @RequestParam(value = EndpointParam.SIZE, defaultValue = EndpointParam.SIZE_DEFAULT) int size) {
        return deliveryService.getListOfDelivery(page, size);
    }

    @GetMapping(FIND_DISPATCH)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for retrieving lists of dispatch", response = DispatchResponse.class, responseContainer = "List")
    public ApiResponse<List<DispatchResponse>, BaseDto> getListOfDispatch(@RequestParam(value = EndpointParam.PAGE, defaultValue = EndpointParam.PAGE_DEFAULT) int page,
                                                                          @RequestParam(value = EndpointParam.SIZE, defaultValue = EndpointParam.SIZE_DEFAULT) int size) {
        return dispatchService.getListOfDispatch(page, size);
    }


    @GetMapping(FIND_PAYMENT)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for retrieving lists of payment", response = PaymentResponse.class, responseContainer = "List")
    public ApiResponse<List<PaymentResponse>, BaseDto> getListOfPayment(@RequestParam(value = EndpointParam.PAGE, defaultValue = EndpointParam.PAGE_DEFAULT) int page,
                                                                          @RequestParam(value = EndpointParam.SIZE, defaultValue = EndpointParam.SIZE_DEFAULT) int size) {
        return paymentService.getListOfPayment(page, size);
    }



                                             //ADD_TRANSACTIONS




    @PostMapping(ADD_DELIVERY)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for adding new delivery to database", response = String.class)
    public ApiResponse<String, BaseDto> addDelivery(@Valid @RequestBody DeliveryRequest request) {
        return deliveryService.addDelivery(request);
    }

    @PostMapping(ADD_DISPATCH)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for adding new dispatch to database", response = String.class)
    public ApiResponse<String, BaseDto> addDispatch(@Valid @RequestBody DispatchDriverRequest request) {
        return dispatchService.addDispatch(request);
    }


                                            //FIND_TRANSACTIONS_BY_ID

    @GetMapping(FIND_DELIVERY_BY_ID)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching Delivery by id from database", response = DeliveryResponse.class)
    public ApiResponse<DeliveryResponse, BaseDto> getDeliveryById(@RequestParam UUID deliveryId) {
        return deliveryService.getDeliveryById(deliveryId);
    }



                                            //UPDATE_TRANSACTION

    @PutMapping(UPDATE_DELIVERY)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for updating delivery by id from database", response = String.class)
    public ApiResponse<String, BaseDto> updateDelivery(@PathVariable(value = "id")  UUID productUuid,
                                                       @RequestBody DeliveryRequest request) {
        return deliveryService.updateDelivery(productUuid, request);
    }

    @PutMapping(UPDATE_PAYMENT)
    @ApiOperation(value = "Endpoint for updating payment by id from database", response = String.class)
    public delivery.management.dto.ApiResponse<String> updatePayment(@PathVariable(value = "id") UUID paymentUuid,
                                                                     @RequestBody PaymentRequest request) {
        return paymentService.updatePayment(paymentUuid, request);
    }




                                    //FIND_LISTS_OF_DELIVERY_BY_DATE

//    @GetMapping(FIND_LISTS_OF_DELIVERY_BY_DATE)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
//    @ApiOperation(value = "Endpoint for retrieving lists of Delivery by date", response = DeliveryResponse.class, responseContainer = "List")
//    public ApiResponse<List<DeliveryResponse>, BaseDto> getListOfDeliveryByDate(@RequestParam String dateCreated) {
//        return deliveryService.findDeliveryByDate(dateCreated);
//    }




                                        //FIND_ORDER_BY_CUSTOMER

    @GetMapping(FIND_DELIVERY_BY_SENDER)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching delivery by sender from database", response = DeliveryResponse.class, responseContainer = "List")
    public ApiResponse<List<DeliveryResponse>, BaseDto> getDeliveryBySender(@RequestParam UUID deliveryUuid) {
        return deliveryService.getDeliveryBySender(deliveryUuid);
    }


                                //FIND_DELIVERY_BY_DELIVERY_NO

    @GetMapping(FIND_DELIVERY_BY_DELIVERY_NO)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching delivery by deliveryNo from database", response = DeliveryResponse.class, responseContainer = "List")
    public delivery.management.dto.ApiResponse<DeliveryResponse> getDeliveryByDeliveryNo(@RequestParam String deliveryNo) {
        return deliveryService.getDeliveryByDeliveryNo(deliveryNo);
    }



                                        //FIND_DELIVERY_BY_EMAIL

    @GetMapping(FIND_DISPATCH_BY_EMAIL)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching delivery by email from database", response = DispatchResponse.class, responseContainer = "List")
    public delivery.management.dto.ApiResponse<DispatchResponse> getDeliveryByEmail(@RequestParam String email) {
        return dispatchService.getDispatchByEmail(email);
    }


                                    //FIND_DISPATCH_BY_DELIVERY_NO

    @GetMapping(FIND_DISPATCH_BY_DISPATCH_NAME)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching dispatch by dispatchName from database", response = DispatchResponse.class, responseContainer = "List")
    public ApiResponse<List<DispatchResponse>, BaseDto> getDispatchByDispatchName(@RequestParam String dispatchName) {
        return dispatchService.getListOfDispatchByName(dispatchName);
    }


                                    //FIND_DISPATCH_BY_DELIVERY_NO

    @GetMapping(FIND_DISPATCH_BY_DELIVERY_NO)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching dispatch by deliveryNo from database", response = DispatchResponse.class, responseContainer = "List")
    public delivery.management.dto.ApiResponse<DispatchResponse> getDispatchByDeliveryNo(@RequestParam String deliveryNo) {
        return dispatchService.getDispatchByDeliveryNo(deliveryNo);
    }


                            //FIND_DISPATCH_BY_DELIVERY_NO

    @GetMapping(FIND_PAYMENT_BY_DELIVERY_NO)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching payment by deliveryNo from database", response = PaymentResponse.class, responseContainer = "List")
    public delivery.management.dto.ApiResponse<PaymentResponse> getPaymentByDeliveryNo(@RequestParam String deliveryNo) {
        return paymentService.getPaymentByDeliveryNo(deliveryNo);
    }



                                        //ADD DELIVERY ORDER PAYMENT
    @PostMapping(ADD_PAYMENT)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for adding new payment to database", response = String.class)
    public ApiResponse<String, BaseDto> addPayment(@Valid @RequestBody PaymentRequest request) {
        return paymentService.addPayment(request);
    }





}