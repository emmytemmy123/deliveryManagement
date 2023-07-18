package delivery.management.services.transaction;

import delivery.management.mapper.Mapper;
import delivery.management.model.dto.BaseDto;
import delivery.management.model.dto.enums.AppStatus;
import delivery.management.model.dto.enums.MessageHelpers;
import delivery.management.model.dto.request.productsRequest.ProductItemsRequest;
import delivery.management.model.dto.request.transactionRequest.DeliveryRequest;
import delivery.management.model.dto.response.othersResponse.ApiResponse;
import delivery.management.model.dto.response.transactionResponse.DeliveryResponse;
import delivery.management.model.entity.products.ProductItems;
import delivery.management.model.entity.transaction.Delivery;
import delivery.management.model.entity.user.Users;
import delivery.management.repo.products.ProductItemsRepository;
import delivery.management.repo.user.UsersRepository;
import delivery.management.utills.MessageUtil;
import delivery.management.exception.RecordNotFoundException;
import delivery.management.repo.transaction.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final UsersRepository usersRepository;
    private final ProductItemsRepository productItemsRepository;


    @Override
    /**
     * @Validate and Find the list of  delivery
     * @Validate if the List of delivery is empty otherwise return record not found*
     * @return the list of delivery and a Success Message
     * * */
    public ApiResponse<List<DeliveryResponse>, BaseDto> getListOfDelivery(int page, int size) {

        List<Delivery> deliveryList = deliveryRepository.findAll(PageRequest.of(page,size)).toList();
        if(deliveryList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(deliveryList, DeliveryResponse.class));

    }

    @Override
    /**
     * @Validating and Finding the list of DeliveryOptional by uuid
     * @Validate if the List of DeliveryOptional is empty otherwise return record not found
     * Create the delivery definition and get the DeliveryOptional by uuid
     * @return the list of DeliveryOptional and a Success Message
     * * */
    public ApiResponse<DeliveryResponse, BaseDto> getDeliveryById(UUID deliveryId) {

        Optional<Delivery> deliveryOptional = deliveryRepository.findByUuid(deliveryId);

        if(deliveryOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        Delivery delivery = deliveryOptional.get();

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(delivery,DeliveryResponse.class));

    }


    private void generateDeliveryNumber(Delivery deliveries){
        Integer serial;
        String deliveryNumber;

        LocalDate localDate = LocalDate.now();
        deliveryNumber ="Tr"+localDate.getYear()+""+localDate.getMonthValue()+""+localDate.getDayOfMonth();

        List<Delivery> listDeliveryForToday = deliveryRepository.findDeliveryForCurrentDate(localDate.getMonthValue(), localDate.getYear());
        if(listDeliveryForToday.isEmpty()){
            deliveries.setDeliveryNo(deliveryNumber+"-1");
            deliveries.setSerialNo(1);
        }else{
            Delivery delivery = listDeliveryForToday.get(0);
            serial = delivery.getSerialNo()+1;
            deliveries.setSerialNo(serial);
            deliveries.setDeliveryNo(deliveryNumber+"-"+serial);
        }

    }



    @Override
    public ApiResponse<String, BaseDto> addDelivery(DeliveryRequest request) {

        Users existingUsers = usersRepository.findByUuid(request.getSenderId())
                .orElseThrow(()->new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        Delivery delivery = new Delivery();

        if (existingUsers.getName() != null && existingUsers.getUsersCategory().equals("sender")) {
            delivery.setPostedBy(existingUsers.getName());
        } else {
            throw new RecordNotFoundException(MessageUtil.INVALID_SENDER);
        }

        delivery.setStatus("Not Completed");
        delivery.setPaymentStatus("Not Paid");
        delivery.setUsers(existingUsers);
        delivery.setReceiverName(request.getReceiverName());
        delivery.setReceiverAddress(request.getReceiverAddress());

        Double deliveryAmount = 0.0;
        Double totalDeliveryAmount = 0.0;
        Double deliveryCostPerKg = 100.0;
        Integer totalWeight = 0;
        Integer totalQuantity =0;

        List<ProductItems> productItemsList = new ArrayList<>();
        generateDeliveryNumber(delivery);

        for (ProductItemsRequest deliveryItems : request.getItems()) {

                ProductItems productItems = new ProductItems();

                productItems.setProductName(deliveryItems.getName());
                productItems.setQuantity(deliveryItems.getQuantity());
                productItems.setModel(deliveryItems.getModel());
                productItems.setColour(deliveryItems.getColour());
                productItems.setWeight(deliveryItems.getWeight());
                productItems.setPhoto(deliveryItems.getPhoto());
                productItems.setStatus(deliveryItems.getStatus());
                productItems.setDescription(deliveryItems.getDescription());
                productItems.setDelivery(delivery);

                deliveryAmount += (deliveryItems.getWeight() * deliveryCostPerKg );
                totalWeight += deliveryItems.getWeight();
                totalQuantity += deliveryItems.getQuantity();

                productItems.setAmount(deliveryAmount);

                productItemsList.add(productItems);

            }

            delivery.setTotalDeliveryAmount(deliveryAmount * totalQuantity);
            delivery.setTotalAmountDue(deliveryAmount * totalQuantity );
            delivery.setPaymentMode(request.getPaymentMode());
            delivery.setTotalQuantity(totalQuantity);
            delivery.setTotalWeight(totalWeight);


        delivery.setProductItemsList(productItemsList);

            deliveryRepository.save(delivery);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                MessageHelpers.CREATE_SUCCESSFUL.message);
    }


    /**
     * @validating productItem by uuid
     * @Validate if the List of productItem is empty otherwise return record not found
     * @return productItem
     * * */
    private ProductItems validateProductItems(UUID uuid){
        Optional<ProductItems> productItemsOptional = productItemsRepository.findByUuid(uuid);
        if(productItemsOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return productItemsOptional.get();
    }


    @Override
    public ApiResponse<String, BaseDto> updateDelivery(UUID productItemUuid, DeliveryRequest request) {

        ProductItems productItems = validateProductItems(productItemUuid);

        productItems.setProductName(productItems.getProductName());
        productItems.setQuantity(productItems.getQuantity());
        productItems.setModel(productItems.getModel());
        productItems.setColour(productItems.getColour());
        productItems.setWeight(productItems.getWeight());
        productItems.setPhoto(productItems.getPhoto());
        productItems.setStatus(productItems.getStatus());
        productItems.setDescription(productItems.getDescription());

        productItemsRepository.save(productItems);

        Delivery delivery = productItems.getDelivery();

        delivery.setTotalDeliveryAmount(delivery.getTotalDeliveryAmount());
        delivery.setTotalAmountDue(delivery.getTotalAmountDue());
        delivery.setPaymentMode(delivery.getPaymentMode());
        delivery.setTotalQuantity(delivery.getTotalQuantity());

        deliveryRepository.save(delivery);

        return new ApiResponse<String, BaseDto>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                MessageHelpers.UPDATE_SUCCESSFUL.message);

    }



    @Override
    public ApiResponse<List<DeliveryResponse>, BaseDto> getDeliveryBySender(UUID senderUuid) {

        List<Delivery> deliveryList = deliveryRepository.findDeliveryBySender(senderUuid);

        if(deliveryList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(deliveryList, DeliveryResponse.class));
    }

    @Override
    public delivery.management.dto.ApiResponse<DeliveryResponse> getDeliveryByDeliveryNo(String deliveryNo) {

        Optional<Delivery> existingDeliveryOption = deliveryRepository.findDeliveryByDeliveryNo(deliveryNo);
        if(existingDeliveryOption.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        Delivery delivery = existingDeliveryOption.get();

        return new delivery.management.dto.ApiResponse<DeliveryResponse>(AppStatus.SUCCESS.label,
                Mapper.convertObject(delivery, DeliveryResponse.class));

    }


//    @Override
//    public ApiResponse<List<DeliveryResponse>, BaseDto> findDeliveryByDate(String dateCreated) {
//
//        List<Delivery> deliveryList = deliveryRepository.searchDeliveryByDateCreated(dateCreated);
//
//        if(deliveryList.isEmpty())
//            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
//
//        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
//                Mapper.convertList(deliveryList, DeliveryResponse.class));
//
//    }




}
