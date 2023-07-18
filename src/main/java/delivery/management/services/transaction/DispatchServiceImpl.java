package delivery.management.services.transaction;


import delivery.management.exception.RecordNotFoundException;
import delivery.management.mapper.Mapper;
import delivery.management.model.dto.BaseDto;
import delivery.management.model.dto.enums.AppStatus;
import delivery.management.model.dto.enums.MessageHelpers;
import delivery.management.model.dto.request.transactionRequest.DispatchDriverRequest;
import delivery.management.model.dto.response.othersResponse.ApiResponse;
import delivery.management.model.dto.response.transactionResponse.DeliveryResponse;
import delivery.management.model.dto.response.transactionResponse.DispatchResponse;
import delivery.management.model.entity.products.ProductItems;
import delivery.management.model.entity.transaction.Delivery;
import delivery.management.model.entity.transaction.DispatchDriver;
import delivery.management.model.entity.user.Users;
import delivery.management.model.entity.user.UsersType;
import delivery.management.repo.transaction.DeliveryRepository;
import delivery.management.repo.transaction.DispatchRepository;
import delivery.management.repo.user.UsersRepository;
import delivery.management.repo.user.UsersTypeRepository;
import delivery.management.utills.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DispatchServiceImpl implements DispatchService  {

    private final DispatchRepository dispatchRepository;
    private final UsersRepository usersRepository;
    private final UsersTypeRepository usersTypeRepository;
    private final DeliveryRepository deliveryRepository;


    @Override
    /**
     * @Validate and Find the list of  dispatch
     * @Validate if the List of dispatch is empty otherwise return record not found
     * @return the list of dispatch and a Success Message
     * * */
    public ApiResponse<List<DispatchResponse>, BaseDto> getListOfDispatch(int page, int size) {

        List<DispatchDriver> dispatchDriverList = dispatchRepository.findAll(PageRequest.of(page,size)).toList();
        if(dispatchDriverList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(dispatchDriverList, DispatchResponse.class));

    }

    @Override
    public ApiResponse<String, BaseDto> addDispatch(DispatchDriverRequest request) {

        Delivery existingDelivery = deliveryRepository.findDeliveryByDeliveryNo(request.getDeliveryNo())
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        Users existingUsers = usersRepository.findUsersByEmail(request.getDriverEmail())
            .orElseThrow(() -> new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        DispatchDriver dispatchDriver = new DispatchDriver();

        dispatchDriver.setSender(existingDelivery.getPostedBy());

        if (existingUsers.getName() != null && existingUsers.getUsersCategory().equals("driver")) {
            dispatchDriver.setDispatchName(existingUsers.getName());
        } else {
            throw new RecordNotFoundException(MessageUtil.INVALID_DRIVER);
        }

        dispatchDriver.setReceiverAddress(existingDelivery.getReceiverAddress());
        dispatchDriver.setTotalAmount(existingDelivery.getTotalDeliveryAmount());

        String deliveryNo = "";
        if(deliveryNo.isEmpty())
        dispatchDriver.setDeliveryNo(existingDelivery.getDeliveryNo());
        else
            return new ApiResponse<>(AppStatus.REJECT.label, HttpStatus.UNAUTHORIZED.value(),
                    MessageHelpers.ORDER_EXISTED.message);

        dispatchDriver.setDeliveryId(String.valueOf(existingDelivery.getId()));

        List<ProductItems> productListCopy = new ArrayList<>(existingDelivery.getProductItemsList());
        dispatchDriver.setProductList(productListCopy);

        dispatchDriver.setReceiverName(existingDelivery.getReceiverName());
        dispatchDriver.setDispatchDate(LocalDate.now().atStartOfDay());

        existingDelivery.setStatus("dispatched");

        dispatchRepository.save(dispatchDriver);
        deliveryRepository.save(existingDelivery);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                MessageHelpers.CREATE_SUCCESSFUL.message);

    }



    @Override
        public ApiResponse<List<DispatchResponse>, BaseDto> getDispatchByName(String dispatchName) {

        List<DispatchDriver> dispatchDriverList = dispatchRepository.findDispatchByDriver(dispatchName);

        if(dispatchDriverList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(dispatchDriverList, DispatchResponse.class));

    }



}
