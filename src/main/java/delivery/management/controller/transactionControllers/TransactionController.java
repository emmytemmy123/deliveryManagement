package delivery.management.controller.transactionControllers;


import delivery.management.model.dto.request.transactionRequest.DeliveryRequest;
import delivery.management.model.dto.request.transactionRequest.PaymentRequest;
import delivery.management.model.dto.response.othersResponse.ApiResponse;
import delivery.management.model.dto.response.transactionResponse.DeliveryResponse;
import delivery.management.model.dto.response.transactionResponse.PaymentResponse;
import delivery.management.services.transaction.DeliveryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import delivery.management.services.transaction.PaymentService;
import delivery.management.utills.EndPoints.TransactionEndPoints;
import delivery.management.utills.EndpointParam;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static delivery.management.utills.EndPoints.TransactionEndPoints.*;

@RestController
@RequestMapping(transaction)
@RequiredArgsConstructor
public class TransactionController {

    private final PaymentService paymentService;
    private final DeliveryService deliveryService;



                                //FIND_LISTS_OF_TRANSACTIONS

    @GetMapping(FIND_PAYMENT)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') ")
    @ApiOperation(value = "Endpoint for retrieving lists of payment", response = PaymentResponse.class, responseContainer = "List")
    public ApiResponse<List<PaymentResponse>> getListOfPayment(@RequestParam(value = EndpointParam.PAGE, defaultValue = EndpointParam.PAGE_DEFAULT) int page,
                                                               @RequestParam(value = EndpointParam.SIZE, defaultValue = EndpointParam.SIZE_DEFAULT) int size) {
        return paymentService.getListOfPayment(page, size);
    }

    @GetMapping(FIND_DELIVERY)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for retrieving lists of delivery", response = DeliveryResponse.class, responseContainer = "List")
    public ApiResponse<List<DeliveryResponse>> getListOfDelivery(@RequestParam(value = EndpointParam.PAGE, defaultValue = EndpointParam.PAGE_DEFAULT) int page,
                                                               @RequestParam(value = EndpointParam.SIZE, defaultValue = EndpointParam.SIZE_DEFAULT) int size) {
        return deliveryService.getListOfDelivery(page, size);
    }



                                             //ADD_TRANSACTIONS

    @PostMapping(TransactionEndPoints.ADD_PAYMENT)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for adding new payment to database", response = String.class)
    public ApiResponse<String> addPayment(@Valid @RequestBody PaymentRequest request) {
        return paymentService.addPayment(request);
    }


    @PostMapping(ADD_DELIVERY)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for adding new delivery to database", response = String.class)
    public ApiResponse<String> addDelivery(@Valid @RequestBody DeliveryRequest request) {
        return deliveryService.addDelivery(request);
    }


                                            //FIND_TRANSACTIONS_BY_ID

    @GetMapping(FIND_DELIVERY_BY_ID)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching Delivery by id from database", response = DeliveryResponse.class)
    public ApiResponse<DeliveryResponse> getDeliveryById(@RequestParam UUID deliveryId) {
        return deliveryService.getDeliveryById(deliveryId);
    }



                                            //UPDATE_TRANSACTION

    @PutMapping(UPDATE_DELIVERY)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for updating delivery by id from database", response = String.class)
    public ApiResponse<String> updateDelivery(@PathVariable(value = "id")  UUID productUuid,
                                             @RequestBody DeliveryRequest request) {
        return deliveryService.updateDelivery(productUuid, request);
    }



                                //FIND_LISTS_OF_PAYMENT_BY_DATE

    @GetMapping(FIND_LISTS_OF_PAYMENT_BY_DATE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') ")
    @ApiOperation(value = "Endpoint for retrieving lists of payment by date", response = PaymentResponse.class, responseContainer = "List")
    public ApiResponse<List<PaymentResponse>> getListOfPayment(@RequestParam String dateCreated) {
        return paymentService.findPaymentByDate(dateCreated);
    }

                                    //FIND_LISTS_OF_DELIVERY_BY_DATE

    @GetMapping(FIND_LISTS_OF_DELIVERY_BY_DATE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for retrieving lists of Delivery by date", response = DeliveryResponse.class, responseContainer = "List")
    public ApiResponse<List<DeliveryResponse>> getListOfDeliveryByDate(@RequestParam String dateCreated) {
        return deliveryService.findDeliveryByDate(dateCreated);
    }

//
//                                    //FIND_PAYMENT_BY_CUSTOMER
//
//    @GetMapping(FIND_PAYMENT_BY_SALES_PERSON)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
//    @ApiOperation(value = "Endpoint for fetching payment by salesPerson from database", response = PaymentResponse.class, responseContainer = "List")
//    public ApiResponse<List<PaymentResponse>> getPaymentBySalesPerson(@RequestParam UUID userId) {
//        return paymentService.findPaymentBySalesPerson(userId);
//    }

                                    //FIND_PAYMENT_BY_CUSTOMER

    @GetMapping(FIND_PAYMENT_BY_CUSTOMER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching payment by customer from database", response = PaymentResponse.class, responseContainer = "List")
    public ApiResponse<List<PaymentResponse>> getPaymentByCustomer(@RequestParam UUID customerId) {
        return paymentService.findPaymentByCustomer(customerId);
    }

                                        //FIND_ORDER_BY_CUSTOMER

    @GetMapping(FIND_DELIVERY_BY_SENDER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching delivery by sender from database", response = DeliveryResponse.class, responseContainer = "List")
    public ApiResponse<List<DeliveryResponse>> getDeliveryBySender(@RequestParam UUID deliveryUuid) {
        return deliveryService.getDeliveryBySender(deliveryUuid);
    }


                                    //FIND_LIST_OF_PAYMENT_BY_DATE_RANGE

    @GetMapping(TransactionEndPoints.FIND_LIST_OF_PAYMENT_BY_DATE_RANGE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for retrieving lists of payment by dateRange", response = PaymentResponse.class, responseContainer = "List")
    public ApiResponse<List<PaymentResponse>> getListOfPaymentByDateRange(@RequestParam String from, @RequestParam String to) {
        return paymentService.findListOfPaymentByDateRange(from, to);
    }


                                //FIND_LIST_OF_PAYMENT_BY_ORDER_ID

    @GetMapping(TransactionEndPoints.FIND_PAYMENT_BY_ORDER_ID)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching payment by orderId from database", response = PaymentResponse.class, responseContainer = "List")
    public ApiResponse<List<PaymentResponse>> getPaymentByOrderId(@RequestParam UUID orderId) {
        return paymentService.getPaymentByDeliveryId(orderId);
    }




}