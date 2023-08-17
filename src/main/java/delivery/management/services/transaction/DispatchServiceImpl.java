package delivery.management.services.transaction;


import delivery.management.exception.RecordNotFoundException;
import delivery.management.mapper.Mapper;
import delivery.management.model.dto.BaseDto;
import delivery.management.model.dto.enums.AppStatus;
import delivery.management.model.dto.enums.MessageHelpers;
import delivery.management.model.dto.request.transactionRequest.DispatchDriverRequest;
import delivery.management.model.dto.response.othersResponse.ApiResponse;
import delivery.management.model.dto.response.transactionResponse.DispatchResponse;
import delivery.management.model.entity.products.ProductItems;
import delivery.management.model.entity.transaction.Delivery;
import delivery.management.model.entity.transaction.DispatchDriver;
import delivery.management.model.entity.user.Users;
import delivery.management.repo.transaction.DeliveryRepository;
import delivery.management.repo.transaction.DispatchRepository;
import delivery.management.repo.user.UsersRepository;
import delivery.management.repo.user.UsersCategoryRepository;
import delivery.management.utills.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DispatchServiceImpl implements DispatchService  {

    private final DispatchRepository dispatchRepository;
    private final UsersRepository usersRepository;
    private final UsersCategoryRepository usersCategoryRepository;
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



    /**
     * @Validating existingDispatchOptional by deliveryNo
     * @Validate if the List of existingDispatchOptional is empty otherwise return Dispatch has been made
     * * */
    private void validateDuplicationDispatch(String deliveryNo){
        Optional<DispatchDriver> existingDispatchOptional = dispatchRepository.findDispatchByDeliveryNo(deliveryNo);
        if(existingDispatchOptional.isPresent())
            throw new RecordNotFoundException("Dispatch is in progress");

    }



    @Override
    public ApiResponse<String, BaseDto> addDispatch(DispatchDriverRequest request) {

        validateDuplicationDispatch(request.getDeliveryNo());

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
        dispatchDriver.setDeliveryNo(existingDelivery.getDeliveryNo());
        dispatchDriver.setDeliveryId(String.valueOf(existingDelivery.getId()));
        dispatchDriver.setReceiverName(existingDelivery.getReceiverName());
        dispatchDriver.setDispatchDate(LocalDate.now().atStartOfDay());
        dispatchDriver.setEmail(existingUsers.getEmail());

        List<ProductItems> productListCopy = new ArrayList<>(existingDelivery.getProductItemsList());
        dispatchDriver.setProductList(productListCopy);

//        dispatchDriver.setProductList(existingDelivery.getProductItemsList());

        existingDelivery.setStatus("In Progress...");
        existingDelivery.setDriverStatus("In Progress...");

        dispatchRepository.save(dispatchDriver);
        deliveryRepository.save(existingDelivery);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                MessageHelpers.CREATE_SUCCESSFUL.message);

    }


    @Override
    public delivery.management.dto.ApiResponse<DispatchResponse> getDispatchByDeliveryNo(String deliveryNo) {

        Optional<DispatchDriver> existingDispatchOption = dispatchRepository.findDispatchByDeliveryNo(deliveryNo);
        if(existingDispatchOption.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        DispatchDriver dispatchDriver = existingDispatchOption.get();

        return new delivery.management.dto.ApiResponse<DispatchResponse>(AppStatus.SUCCESS.label,
                Mapper.convertObject(dispatchDriver, DispatchResponse.class));
    }

    @Override
    public ApiResponse<List<DispatchResponse>, BaseDto> getListOfDispatchByName(String dispatchName) {

        List<DispatchDriver> dispatchDriverList = dispatchRepository.findDispatchByDispatchName(dispatchName);

        if(dispatchDriverList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(dispatchDriverList, DispatchResponse.class));

    }


    @Override
    public delivery.management.dto.ApiResponse<DispatchResponse> getDispatchByEmail(String email) {

        Optional<DispatchDriver> existingDispatchOption = dispatchRepository.findDispatchByEmail(email);

        if(existingDispatchOption.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        DispatchDriver dispatchDriver = existingDispatchOption.get();

        return new delivery.management.dto.ApiResponse<DispatchResponse>(AppStatus.SUCCESS.label,
                Mapper.convertObject(dispatchDriver, DispatchResponse.class));

    }


}
