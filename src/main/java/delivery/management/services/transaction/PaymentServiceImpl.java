package delivery.management.services.transaction;


import delivery.management.exception.RecordNotFoundException;
import delivery.management.mapper.Mapper;
import delivery.management.model.dto.BaseDto;
import delivery.management.model.dto.enums.AppStatus;
import delivery.management.model.dto.enums.Status;
import delivery.management.model.dto.request.transactionRequest.PaymentRequest;
import delivery.management.model.dto.response.othersResponse.ApiResponse;
import delivery.management.model.dto.response.transactionResponse.DeliveryResponse;
import delivery.management.model.dto.response.transactionResponse.PaymentResponse;
import delivery.management.model.entity.transaction.Delivery;
import delivery.management.model.entity.transaction.DispatchDriver;
import delivery.management.model.entity.transaction.Payment;
import delivery.management.repo.transaction.DeliveryRepository;
import delivery.management.repo.transaction.DispatchRepository;
import delivery.management.repo.transaction.PaymentRepository;
import delivery.management.utills.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final DeliveryRepository deliveryRepository;
    private final DispatchRepository dispatchRepository;


    @Override
    /**
     * @Validate and Find the list of  payment
     * @Validate if the List of payment is empty otherwise return record not found
     * @return the list of payment and a Success Message
     * * */
    public ApiResponse<List<PaymentResponse>, BaseDto> getListOfPayment(int page, int size) {

        List<Payment>  paymentList = paymentRepository.findAll(PageRequest.of(page, size)).toList();
        if(paymentList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(paymentList, PaymentResponse.class));

    }


    @Override
    /**
     * @Validate that no duplicate Payment is allowed
     * @Validate that deliveryOrder exists otherwise return record not found
     * Create the Payment definition base on the order and save
     * @return success message
     * * */
    public ApiResponse<String, BaseDto> addPayment(PaymentRequest request) {

        Delivery existingDelivery = deliveryRepository.findDeliveryByDeliveryNo(request.getDeliveryNo())
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        Optional<DispatchDriver> existingDispatch = Optional.ofNullable(dispatchRepository.findDispatchByDeliveryNo(request.getDispatchDeliveryNo())
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND)));

        Payment payment = new Payment();

        payment.setPaidBy(existingDispatch.get().getReceiverName());
        payment.setAmountPaid(existingDispatch.get().getTotalAmount());
        payment.setPaymentMode(existingDelivery.getPaymentMode());
        payment.setPaymentDate(LocalDate.now().atStartOfDay());
        payment.setPaidTo(existingDispatch.get().getDispatchName());
        payment.setStatus("paid");
        payment.setDeliveryNo(existingDispatch.get().getDeliveryNo());


        existingDelivery.setPaymentStatus("paid");

        deliveryRepository.save(existingDelivery);
        paymentRepository.save(payment);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Payment made successfully");

    }




    @Override
    public ApiResponse<List<PaymentResponse>, BaseDto> getPaymentBySender(UUID paymentUuid) {

        return null;


    }



}
