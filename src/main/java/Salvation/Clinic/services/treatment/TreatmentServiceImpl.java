package Salvation.Clinic.services.treatment;


import Salvation.Clinic.mapper.Mapper;
import Salvation.Clinic.model.dto.enums.AppStatus;
import Salvation.Clinic.model.dto.request.TreatmentRequest.TreatmentRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.TreatmentResponse.TreatmentResponse;
import Salvation.Clinic.model.dto.response.exception.RecordNotFoundException;
import Salvation.Clinic.model.entity.drug.Drugs;
import Salvation.Clinic.model.entity.transaction.Orders;
import Salvation.Clinic.model.entity.treatment.Treatment;
import Salvation.Clinic.model.entity.users.Users;
import Salvation.Clinic.repo.drugRepo.DrugRepo;
import Salvation.Clinic.repo.treatmentRepo.TreatmentRepo;
import Salvation.Clinic.repo.userRepo.UserRepo;
import Salvation.Clinic.utills.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepo treatmentRepo;
    private final UserRepo userRepo;
    private final DrugRepo drugRepo;


    @Override
    /**
     * @Finding the list of treatments
     * @Validate if the List of treatments is empty otherwise return record not found
     * @return the list of treatments and a Success Message
     * * */
    public ApiResponse<List<TreatmentResponse>> getListOfTreatment(int page, int size) {
        List<Treatment> treatmentList = treatmentRepo.findAll(PageRequest.of(page, size)).toList();
        if(treatmentList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, Mapper.convertList(treatmentList, TreatmentResponse.class ));

    }


    @Override
    /**
     * @Validate that user creating giving the treatment exists, otherwise return user not found
     * Create the treatment definition and save
     * @return success message
     * * */
    public ApiResponse<String> addTreatment(TreatmentRequest request) {

        Users existingPatient = userRepo.findByUuid(request.getPatientId())
                .orElseThrow( () -> new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        Users existingStaff = userRepo.findByUuid(request.getTreatedById())
                .orElseThrow( () -> new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        Drugs existingDrugs = drugRepo.findByUuid(request.getDrugId())
                .orElseThrow( () -> new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        Treatment treatment = new Treatment();

        treatment.setPatientName(existingPatient.getName());
        treatment.setTreatedBy(existingStaff.getName());
        treatment.setSickness(request.getSickness());
        treatment.setTreatmentAmount(request.getCost());
        treatment.setDrugs(existingDrugs.getName());
        treatment.setDescription(request.getDescription());
        treatment.setStatus("stabilized");
        treatment.setUsers(existingPatient);

//        Orders orders = (Orders) treatment.getOrdersList();
//
//        treatment.setFinalAmount(orders.getGrandTotal());

        treatmentRepo.save(treatment);

        return new ApiResponse<>(AppStatus.SUCCESS.label, "Record added Successfully");
    }

    public Treatment validateTreatmentByUuid(UUID uuid){
        Optional<Treatment> treatmentOptional = treatmentRepo.findByUuid(uuid);
        if(treatmentOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return treatmentOptional.get();
    }


    @Override
    /**
     * @validating treatmentOptional by uuid
     * @Validate if the List of treatmentOptional is empty otherwise return record not found
     * Create the treatmentOptional definition and update
     * @return a Success Message
     * * */
    public ApiResponse<String> updateTreatment(UUID treatmentUuid, TreatmentRequest request) {
        Treatment treatment = validateTreatmentByUuid(treatmentUuid);

        if(request.getDrugs() != null){
            treatment.setDrugs(request.getDrugs());
        }
        if(request.getTreatedBy() != null){
            treatment.setTreatedBy(request.getTreatedBy());
        }
        if(request.getSickness() != null){
            treatment.setSickness(request.getSickness());
        }
        if(request.getCost() != null){
            treatment.setTreatmentAmount(request.getCost());
        }
        if(request.getDescription() != null){
            treatment.setDescription(request.getDescription());
        }
        if(request.getStatus() != null){
            treatment.setStatus("to be admitted");
        }

        treatmentRepo.save(treatment);
        return new ApiResponse<>(AppStatus.SUCCESS.label, "Record Updated Successfully");
    }


    @Override
    /**
     * @validating treatmentOptional by uuid
     * @Validate if the List of treatmentOptional is empty otherwise return record not found
     * Create the treatmentOptional definition and update
     * @return a Success Message
     * * */
    public ApiResponse<String> deleteTreatment(UUID treatmentUuid) {
        Treatment treatment = validateTreatmentByUuid(treatmentUuid);
        treatmentRepo.delete(treatment);
        return new ApiResponse<>(AppStatus.SUCCESS.label, "Record Deleted Successfully");
    }




}
