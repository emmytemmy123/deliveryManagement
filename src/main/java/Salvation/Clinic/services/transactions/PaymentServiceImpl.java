package Salvation.Clinic.services.transactions;

import Salvation.Clinic.mapper.Mapper;
import Salvation.Clinic.model.dto.enums.AppStatus;
import Salvation.Clinic.model.dto.enums.Status;
import Salvation.Clinic.model.dto.request.TransactionRequest.PaymentRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.TransactionResponse.PaymentResponse;
import Salvation.Clinic.model.dto.response.exception.RecordNotFoundException;
import Salvation.Clinic.model.entity.transaction.Orders;
import Salvation.Clinic.model.entity.transaction.Payment;
import Salvation.Clinic.model.entity.treatment.Admission;
import Salvation.Clinic.model.entity.treatment.Treatment;
import Salvation.Clinic.repo.transactionRepo.OrderRepo;
import Salvation.Clinic.repo.transactionRepo.PaymentRepo;
import Salvation.Clinic.repo.treatmentRepo.AdmissionRepo;
import Salvation.Clinic.repo.treatmentRepo.TreatmentRepo;
import Salvation.Clinic.repo.userRepo.UserRepo;
import Salvation.Clinic.utills.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{
    private final UserRepo userRepo;
    private final OrderRepo orderRepo;
    private final TreatmentRepo treatmentRepo;
    private final AdmissionRepo admissionRepo;
    private final PaymentRepo paymentRepo;



    @Override
    /**
     * @Validate and Find the list of  Payment
     * @Validate if the List of Payment is empty otherwise return record not found
     * @return the list of Payment
     * * */
    public ApiResponse<List<PaymentResponse>> getListOfPayment(int page, int size) {
        List<Payment> paymentList = paymentRepo.findAll(PageRequest.of(page, size)).toList();
        if(paymentList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return new ApiResponse<>(AppStatus.SUCCESS.label, Mapper.convertList(paymentList, PaymentResponse.class));
    }


    @Override
    /**
     * @Validate that existingOrders exists otherwise return record not found
     * @Validate that existingTreatment exists otherwise return record not found
     *  @Validate that existingAdmission exists otherwise return record not found
     * Create the Payment definition and save
     * @return success message
     * * */
    public ApiResponse<String> addPayment(PaymentRequest request) {
        Orders existingOrder = orderRepo.findByUuid(request.getDrugOrderId())
                .orElseThrow( () -> new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        Payment payment = new Payment();

        payment.setAmountPaid(request.getAmountPaid());
        payment.setDescription(request.getDescription());
        payment.setPaymentMode(request.getPaymentMode());
        payment.setPaidBy(existingOrder.getAdministerDrugTo());

        int valueToBalance = (int) (existingOrder.getAmountDue() - request.getAmountPaid());
        payment.setPaymentStatus(valueToBalance ==0? Status.PAID.label: Status.TO_BALANCE.label );
        existingOrder.setDrugStatus( valueToBalance ==0? Status.PAID.label: Status.TO_BALANCE.label);
        existingOrder.getTreatment().setStatus(valueToBalance ==0? Status.PAID.label: Status.TO_BALANCE.label);

        payment.setTotalAmount(existingOrder.getGrandTotal());
        payment.setBalance(existingOrder.getGrandTotal() - request.getAmountPaid());

        payment.setOrders(existingOrder);
        payment.setTreatment(existingOrder.getTreatment());

        existingOrder.setPayment(payment);
        existingOrder.setAmountPaid(request.getAmountPaid());
        existingOrder.setAmountDue(existingOrder.getAmountDue() - payment.getAmountPaid());
        orderRepo.save(existingOrder);

        payment.setAmountPaid(request.getAmountPaid());
        paymentRepo.save(payment);

        return new ApiResponse<>(AppStatus.SUCCESS.label, "Payment Made Successfully");
    }



    @Override
    public ApiResponse<String> updatePayment(UUID paymentUuid, PaymentRequest request) {
        return null;
    }

    @Override
    public ApiResponse<String> deletePayment(UUID paymentUuid) {
        return null;
    }


    @Override
    public ApiResponse<List<PaymentResponse>> findListOfPaymentByDateRange(String from, String to) {
        List<Payment> paymentList = paymentRepo.findByDateRange(from, to);
        if(paymentList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return new ApiResponse<>(AppStatus.SUCCESS.label, Mapper.convertList(paymentList, PaymentResponse.class));
    }




}
