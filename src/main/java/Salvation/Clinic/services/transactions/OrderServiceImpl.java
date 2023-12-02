package Salvation.Clinic.services.transactions;


import Salvation.Clinic.mapper.Mapper;
import Salvation.Clinic.model.dto.enums.AppStatus;
import Salvation.Clinic.model.dto.request.TransactionRequest.DrugOrderItemsRequest;
import Salvation.Clinic.model.dto.request.TransactionRequest.OrderRequest;
import Salvation.Clinic.model.dto.request.TransactionRequest.UpdateOrderRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.TransactionResponse.OrderResponse;
import Salvation.Clinic.model.dto.response.exception.BadRequestException;
import Salvation.Clinic.model.dto.response.exception.RecordNotFoundException;
import Salvation.Clinic.model.entity.drug.Drugs;
import Salvation.Clinic.model.entity.transaction.DrugOrderItems;
import Salvation.Clinic.model.entity.transaction.Orders;
import Salvation.Clinic.model.entity.treatment.Admission;
import Salvation.Clinic.model.entity.treatment.Treatment;
import Salvation.Clinic.model.entity.users.Users;
import Salvation.Clinic.model.entity.users.UsersCategory;
import Salvation.Clinic.repo.drugRepo.DrugRepo;
import Salvation.Clinic.repo.transactionRepo.DrugOrderItemsRepo;
import Salvation.Clinic.repo.transactionRepo.OrderRepo;
import Salvation.Clinic.repo.treatmentRepo.AdmissionRepo;
import Salvation.Clinic.repo.treatmentRepo.TreatmentRepo;
import Salvation.Clinic.repo.userRepo.UserCategoryRepo;
import Salvation.Clinic.repo.userRepo.UserRepo;
import Salvation.Clinic.utills.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepo orderRepo;
  private final UserRepo userRepo;
  private final DrugRepo drugRepo;
  private final UserCategoryRepo userCategoryRepo;
  private final AdmissionRepo admissionRepo;
  private final TreatmentRepo treatmentRepo;
  private final DrugOrderItemsRepo drugOrderItemsRepo;


 @Override
 /**
  * @Finding the list of drugOrder
  * @Validate if the List of drugOrder is empty otherwise return record not found
  * @return the list of drugOrder and a Success Message
  * * */
 public ApiResponse<List<OrderResponse>> getListOfDrugOrder(int page, int size) {
   List<Orders> drugOrderList = orderRepo.findAll(PageRequest.of(page, size)).toList();
   if(drugOrderList.isEmpty())
    throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
   return new ApiResponse<>(AppStatus.SUCCESS.label, Mapper.convertList(drugOrderList, OrderResponse.class));
 }

    private void generateOrderNumber(Orders  orders){
        Integer serial;
        String orderNumber;

        LocalDate localDate = LocalDate.now();
        orderNumber ="Tr"+localDate.getYear()+""+localDate.getMonthValue()+""+localDate.getDayOfMonth();

        List<Orders> listOrderForToday = orderRepo.findOrderForCurrentDate(localDate.getMonthValue(), localDate.getYear());
        if(listOrderForToday.isEmpty()){
            orders.setOrderNo(orderNumber+"-1");
            orders.setSerialNo(1);
        }else{
            Orders order = listOrderForToday.get(0);
            serial = order.getSerialNo()+1;
            orders.setSerialNo(serial);
            orders.setOrderNo(orderNumber+"-"+serial);
        }
    }


 @Override
 public ApiResponse<String> addDrugOrder(OrderRequest request) {
     Users existingStaff = userRepo.findByUuid(request.getOrderedById())
             .orElseThrow( () -> new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

     UsersCategory existingUserCategory = userCategoryRepo.findByUuid(request.getUserCategoryId())
             .orElseThrow( () -> new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

     Treatment existingTreatment = treatmentRepo.findByUuid(request.getTreatmentId())
             .orElseThrow( () -> new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

     Orders drugOrder = new Orders();

     if(existingStaff.getName() !=null && existingUserCategory.getName().equals("moderator")){
         drugOrder.setOrderBy(existingStaff.getName());
     } else if(existingStaff.getName() != null && existingUserCategory.getName().equals("admin")){
         drugOrder.setOrderBy(existingStaff.getName());
     } else{
         throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
     }

    drugOrder.setUsers(existingStaff);
    drugOrder.setAdministerDrugTo(existingTreatment.getPatientName());
    drugOrder.setOrderBy(existingStaff.getName());
    drugOrder.setTreatedBy(existingTreatment.getTreatedBy());

    Double totalAmount = 0.0;
    Integer totalQuantity = 0;
    List<DrugOrderItems> drugOrderItemsList = new ArrayList<>();
    generateOrderNumber(drugOrder);

     for (DrugOrderItemsRequest drugOrderItem : request.getItems()) {
         Drugs existingDrug = drugRepo.findByUuid(drugOrderItem.getDrugId()).orElse(null);

         if(existingDrug != null) {
             DrugOrderItems drugOrderItems = new DrugOrderItems();

             drugOrderItems.setItemName(existingDrug.getName());
             drugOrderItems.setSalesPrice(existingDrug.getSalesPrice());
             drugOrderItems.setQuantity(drugOrderItem.getQuantity());
             drugOrderItems.setPurchasePrice(existingDrug.getPurchasePrice());
             drugOrderItems.setPurchaseAmount(existingDrug.getPurchasePrice() * drugOrderItem.getQuantity());
             drugOrderItems.setDescription(drugOrderItem.getDescription());
             drugOrderItems.setTransactionDate(LocalDateTime.now());
             drugOrderItems.setStatus("drug pulled");
             drugOrderItems.setAmount( existingDrug.getSalesPrice() * drugOrderItem.getQuantity() );
             drugOrderItems.setProfit(drugOrderItems.getAmount() - drugOrderItems.getPurchaseAmount());
             drugOrderItems.setDrugCategory(existingDrug.getDrugCategory().getName());
             drugOrderItems.setOrders(drugOrder);
             drugOrderItems.setDrugs(existingDrug);
             drugOrderItems.setOrderBy(existingStaff.getName());
             drugOrderItems.setAdministerTo(existingTreatment.getPatientName());
             drugOrderItems.setQuantity(drugOrderItem.getQuantity());
             existingDrug.setQuantity(existingDrug.getQuantity() - drugOrderItems.getQuantity());
             drugOrderItems.setCreatedBy(existingStaff);

             totalAmount += existingDrug.getSalesPrice() * drugOrderItem.getQuantity();
             totalQuantity += drugOrderItem.getQuantity();

             drugOrderItemsList.add(drugOrderItems);
         }
            drugOrder.setDrugAmount(totalAmount);
            drugOrder.setTotalQuantity(totalQuantity);
            drugOrder.setDrugs(existingDrug);
            drugOrder.setDrugOrderItemsList(drugOrderItemsList);
            drugOrder.setTreatmentAmount(existingTreatment.getTreatmentAmount());
            drugOrder.setTreatment(existingTreatment);

            if(existingTreatment.getStatus().equals("stabilized")){
                drugOrder.setGrandTotal(existingTreatment.getTreatmentAmount() + totalAmount);
                drugOrder.setAmountDue(existingTreatment.getTreatmentAmount() + totalAmount);
            }else{
                drugOrder.setAdmissionAmount(existingTreatment.getAdmission().getAmount());
                drugOrder.setRoomNo(existingTreatment.getAdmission().getRoomNo());
                drugOrder.setGrandTotal(existingTreatment.getTreatmentAmount()
                        + existingTreatment.getAdmission().getAmount() + totalAmount);
                drugOrder.setAmountDue(existingTreatment.getTreatmentAmount()
                        + existingTreatment.getAdmission().getAmount() + totalAmount);
            }


         Integer msg = existingDrug.getQuantity();
            if(msg <= -1)
            throw new BadRequestException(MessageUtil.OUT_OF_STOCK);

            drugOrder.setDrugStatus("not paid");

            orderRepo.save(drugOrder);
    }

     return new ApiResponse<>(AppStatus.SUCCESS.label,  "Drugs Ordered Successfully");
 }


 private DrugOrderItems validateDrugOrderByUuid(UUID uuid){
     Optional<DrugOrderItems> drugOrderItemsOptional = drugOrderItemsRepo.findByUuid(uuid);
     if(drugOrderItemsOptional.isEmpty())
         throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
     return drugOrderItemsOptional.get();
 }


 @Override
 public ApiResponse<String> updateDrugOrder(UUID orderItemUuid, UpdateOrderRequest request) {
     DrugOrderItems drugOrderItems = validateDrugOrderByUuid(orderItemUuid);

     Drugs drugs = drugOrderItems.getDrugs();
     drugs.setQuantity(Integer.sum(drugs.getQuantity(), drugOrderItems.getQuantity() - request.getQuantity()));

     drugOrderItems.setItemName(drugOrderItems.getItemName());
     drugOrderItems.setSalesPrice(drugOrderItems.getSalesPrice());
     drugOrderItems.setPurchasePrice(drugOrderItems.getPurchasePrice());
     drugOrderItems.setPurchaseAmount(drugOrderItems.getPurchasePrice() * request.getQuantity());
     drugOrderItems.setProfit(drugOrderItems.getAmount() - drugOrderItems.getPurchaseAmount());
     drugOrderItems.setDescription(drugOrderItems.getDescription());
     drugOrderItems.setTransactionDate(LocalDateTime.now());
     drugOrderItems.setStatus("drug pulled");


     Integer existingDrug = drugs.getQuantity();
     if(existingDrug <= 0)
         throw  new BadRequestException(MessageUtil.OUT_OF_STOCK);

     int currentQuantity = drugOrderItems.getQuantity();
     int newQuantity = request.getQuantity();
     int quantityDifference = newQuantity - currentQuantity;

     Double newAmount = 0.0;
     newAmount = drugOrderItems.getSalesPrice() * request.getQuantity();

     Orders orders = drugOrderItems.getOrders();

     orders.setDrugAmount((orders.getDrugAmount() + newAmount - drugOrderItems.getAmount()));
     orders.setGrandTotal(orders.getGrandTotal() + newAmount - drugOrderItems.getAmount());
     orders.setTotalQuantity(orders.getTotalQuantity() + quantityDifference);

     drugOrderItems.setQuantity(request.getQuantity());
     drugOrderItems.setAmount(drugOrderItems.getSalesPrice() * request.getQuantity());


     drugOrderItemsRepo.save(drugOrderItems);

     orderRepo.save(orders);

     return new ApiResponse<>(AppStatus.SUCCESS.label, "Record Updated Successfully");
 }


    /**
     * @validating drugOrder by uuid
     * @Validate if the List of drugOrder is empty otherwise return record not found
     * @return drugOrder
     * * */
    private Orders validateDrugOrders(UUID uuid){
        Optional<Orders> ordersOptional = orderRepo.findByUuid(uuid);
        if(ordersOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return ordersOptional.get();
    }

 @Override
 public ApiResponse<String> deleteDrugOrder(UUID drugOrderUuid) {
     Orders orders = validateDrugOrders(drugOrderUuid);
  orderRepo.delete(orders);
  return new ApiResponse<>(AppStatus.SUCCESS.label, "Record Deleted Successfully");
 }


}
