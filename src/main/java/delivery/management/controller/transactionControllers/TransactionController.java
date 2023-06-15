package delivery.management.controller.transactionControllers;


import delivery.management.model.dto.BaseDto;
import delivery.management.model.dto.request.transactionRequest.DeliveryRequest;
import delivery.management.model.dto.response.othersResponse.ApiResponse;
import delivery.management.model.dto.response.transactionResponse.DeliveryResponse;
import delivery.management.services.transaction.DeliveryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import delivery.management.utills.EndpointParam;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static delivery.management.utills.EndPoints.TransactionEndPoints.*;

@RestController
@RequestMapping(transaction)
@RequiredArgsConstructor
public class TransactionController {

    private final DeliveryService deliveryService;



                                //FIND_LISTS_OF_TRANSACTIONS

    @GetMapping(FIND_DELIVERY)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for retrieving lists of delivery", response = DeliveryResponse.class, responseContainer = "List")
    public ApiResponse<List<DeliveryResponse>, BaseDto> getListOfDelivery(@RequestParam(value = EndpointParam.PAGE, defaultValue = EndpointParam.PAGE_DEFAULT) int page,
                                                                          @RequestParam(value = EndpointParam.SIZE, defaultValue = EndpointParam.SIZE_DEFAULT) int size) {
        return deliveryService.getListOfDelivery(page, size);
    }



                                             //ADD_TRANSACTIONS




    @PostMapping(ADD_DELIVERY)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for adding new delivery to database", response = String.class)
    public ApiResponse<String, BaseDto> addDelivery(@Valid @RequestBody DeliveryRequest request) {
        return deliveryService.addDelivery(request);
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




                                    //FIND_LISTS_OF_DELIVERY_BY_DATE

    @GetMapping(FIND_LISTS_OF_DELIVERY_BY_DATE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for retrieving lists of Delivery by date", response = DeliveryResponse.class, responseContainer = "List")
    public ApiResponse<List<DeliveryResponse>, BaseDto> getListOfDeliveryByDate(@RequestParam String dateCreated) {
        return deliveryService.findDeliveryByDate(dateCreated);
    }




                                        //FIND_ORDER_BY_CUSTOMER

    @GetMapping(FIND_DELIVERY_BY_SENDER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching delivery by sender from database", response = DeliveryResponse.class, responseContainer = "List")
    public ApiResponse<List<DeliveryResponse>, BaseDto> getDeliveryBySender(@RequestParam UUID deliveryUuid) {
        return deliveryService.getDeliveryBySender(deliveryUuid);
    }





}