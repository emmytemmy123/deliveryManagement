package delivery.management.services.transaction;

import delivery.management.mapper.Mapper;
import delivery.management.model.dto.enums.AppStatus;
import delivery.management.model.dto.request.transactionRequest.PaymentRequest;
import delivery.management.model.dto.response.othersResponse.ApiResponse;
import delivery.management.model.dto.response.transactionResponse.PaymentResponse;
import delivery.management.model.entity.transaction.Delivery;
import delivery.management.model.entity.transaction.Payment;
import delivery.management.repo.user.CustomerRepository;
import delivery.management.repo.user.UserRepository;
import delivery.management.repo.products.ProductItemsRepository;
import delivery.management.utills.MessageUtil;
import delivery.management.exception.RecordNotFoundException;
import delivery.management.model.dto.enums.Status;
import delivery.management.repo.transaction.DeliveryRepository;
import delivery.management.repo.transaction.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private  final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final ProductItemsRepository productItemsRepository;
    private final DeliveryRepository deliveryRepository;



    @Override
    /**
     * @Validate and Find the list of  Payment
     * @Validate if the List of Payment is empty otherwise return record not found*
     * @return the list of Payment and a Success Message
     * * */
    public ApiResponse<List<PaymentResponse>> getListOfPayment(int page, int size) {
        List<Payment> paymentList = paymentRepository.findAll(PageRequest.of(page,size)).toList();
        if(paymentList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(paymentList, PaymentResponse.class));
    }


    private void generatePaymentNumber(Payment  payment){
        Integer serial;
        String paymentNumber;

        LocalDate localDate = LocalDate.now();
        paymentNumber ="Paid"+localDate.getYear()+""+localDate.getMonthValue()+""+localDate.getDayOfMonth();

        List<Payment> listPaymentForToday = paymentRepository.findPaymentForCurrentDate(localDate.getMonthValue(), localDate.getYear());
        if(listPaymentForToday.isEmpty()){
            payment.setTranReference(paymentNumber+"-1");
            payment.setSerialNo(1);
        }else{
            Payment payments = listPaymentForToday.get(0);
            serial = payments.getSerialNo()+1;
            payment.setSerialNo(serial);
            payment.setTranReference(paymentNumber+"-"+serial);
        }
    }



    @Override
    /**
     * @Validate that no duplicate Payment is allowed
     * @Validate that PaymentCategory exists otherwise return record not found
     * Create the Payment definition and save
     * @return success message
     * * */
    public ApiResponse<String> addPayment(PaymentRequest request) {

        Delivery existingDelivery = deliveryRepository.findByUuid(request.getDeliveryId())
                .orElseThrow(()->new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        Payment payment = new Payment();

        payment.setAmount(request.getAmount());
        payment.setPaymentMode(existingDelivery.getPaymentMode());
        generatePaymentNumber(payment);

        int valueToBalance = (int) (existingDelivery.getTotalAmountDue() - (request.getAmount()));
//        payment.setPaymentStatus(valueToBalance==0? "paid": "balance your payment");
        payment.setPaymentStatus(valueToBalance==0? Status.PAID.label: Status.TO_BALANCE.label);

        payment.setBalance(existingDelivery.getTotalAmountDue() - request.getAmount());
        payment.setPaidBy(existingDelivery.getDispatchTo());
        payment.setDelivery(existingDelivery);

        paymentRepository.save(payment);

        Delivery delivery = payment.getDelivery();
        delivery.setTotalAmountDue(delivery.getTotalAmountDue() - (request.getAmount()));

        deliveryRepository.save(delivery);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record created successfully");

    }



    /**
     * @validating PaymentOptional by uuid
     * @Validate if the List of Payment is empty otherwise return record not found
     * @return PaymentOptional
     * * */
    private Payment validatePayment(UUID uuid){
        Optional<Payment> paymentOptional = paymentRepository.findByUuid(uuid);
        if(paymentOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return paymentOptional.get();
    }


    @Override
    public ApiResponse<List<PaymentResponse>> findPaymentByDate(String dateCreated) {

        List<Payment> paymentList = paymentRepository.findByDateCreated(dateCreated);

        if(paymentList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(paymentList, PaymentResponse.class));

    }



    @Override
    public ApiResponse<List<PaymentResponse>> findPaymentByCustomer(UUID uuid) {

        List<Payment> paymentList = paymentRepository.findPaymentByCustomer(uuid);

        if(paymentList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);


        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(paymentList,PaymentResponse.class));

    }


    @Override
    public ApiResponse<List<PaymentResponse>> getPaymentByDeliveryId(UUID deliveryId) {

        List<Payment> paymentList = paymentRepository.findByDeliveryId(deliveryId);

        if(paymentList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(paymentList,PaymentResponse.class));

    }


    @Override
    public ApiResponse<List<PaymentResponse>> findListOfPaymentByDateRange(String from, String to) {
        List<Payment> paymentList = paymentRepository.findByDateCreatedBetween(from,to);

        if(paymentList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(paymentList, PaymentResponse.class));
    }



}
