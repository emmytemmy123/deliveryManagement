package Salvation.Clinic.services.treatment;


import Salvation.Clinic.mapper.Mapper;
import Salvation.Clinic.model.dto.enums.AppStatus;
import Salvation.Clinic.model.dto.request.TreatmentRequest.AdmissionRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.TreatmentResponse.AdmissionResponse;
import Salvation.Clinic.model.dto.response.exception.BadRequestException;
import Salvation.Clinic.model.dto.response.exception.RecordNotFoundException;
import Salvation.Clinic.model.entity.room.Room;
import Salvation.Clinic.model.entity.treatment.Admission;
import Salvation.Clinic.model.entity.treatment.Treatment;
import Salvation.Clinic.model.entity.users.Users;
import Salvation.Clinic.repo.roomRepo.RoomRepo;
import Salvation.Clinic.repo.treatmentRepo.AdmissionRepo;
import Salvation.Clinic.repo.treatmentRepo.TreatmentRepo;
import Salvation.Clinic.repo.userRepo.UserRepo;
import Salvation.Clinic.utills.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdmissionServiceImpl implements AdmissionService {
    private final AdmissionRepo admissionRepo;
    private final UserRepo userRepo;
    private final TreatmentRepo treatmentRepo;
    private final RoomRepo roomRepo;



    @Override
    /**
     * @Finding the list of admission
     * @Validate if the List of admission is empty otherwise return record not found
     * @return the list of admission and a Success Message
     * * */
    public ApiResponse<List<AdmissionResponse>> getListOfAdmission(int page, int size) {
        List<Admission> admissionList = admissionRepo.findAll(PageRequest.of(page, size)).toList();
        if(admissionList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return new ApiResponse<>(AppStatus.SUCCESS.label, Mapper.convertList(admissionList, AdmissionResponse.class));
    }

    @Override
    /**
     * @Validate that user creating or giving the treatment exists, otherwise return user not found
     * Create the admission definition and save
     * @return success message
     * * */
    public ApiResponse<String> addAdmission(AdmissionRequest request) {
        Users existingStaff = userRepo.findByUuid(request.getCreatedById())
                .orElseThrow( () -> new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        Treatment existingTreatment = treatmentRepo.findByUuid(request.getTreatmentId())
                .orElseThrow( () -> new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        Room existingRoom = roomRepo.findByUuid(request.getRoomId())
                .orElseThrow( () -> new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        Admission admission = new Admission();

        if(existingTreatment != null && existingTreatment.getStatus().equals("to be admitted"))
        admission.setStatus("On Admission");
        else
            throw new RecordNotFoundException(MessageUtil.INVALID_STATUS);

        admission.setRoomNo(existingRoom.getRoomNo());
        admission.setRoom(existingRoom);
        admission.setAdmissionDate(LocalDateTime.now());
        admission.setDescription(request.getDescription());
        admission.setName(existingTreatment.getPatientName());
        admission.setTreatedBy(existingStaff.getName());
        admission.setTreatment(existingTreatment);
        admission.setNoOfNights(request.getNoOfNights());
        admission.setAmount(existingRoom.getCost() * request.getNoOfNights());

        if (existingRoom != null && existingRoom.getStatus().equals("available") ) {
            admission.setStatus("occupied");
            existingTreatment.setStatus("admitted");
            existingRoom.setNoOfBed(existingRoom.getNoOfBed() - 1 );
        }else{
            throw new BadRequestException("Bed not Available");
        }

        if(existingRoom != null && existingRoom.getNoOfBed() <= 0){
            existingRoom.setStatus("unAvailable");
        }

        if(existingRoom != null && existingRoom.getNoOfBed() <= -1){
            throw new BadRequestException("No more bed space");
        }

        admissionRepo.save(admission);

        Treatment treatment = admission.getTreatment();
        treatment.setAdmission(admission);

        treatmentRepo.save(treatment);

        return new ApiResponse<>(AppStatus.SUCCESS.label, "Record Added Successfully");
    }

    public Admission validateTreatmentByUuid(UUID uuid){
        Optional<Admission> admissionOptional = admissionRepo.findByUuid(uuid);
        if(admissionOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return admissionOptional.get();
    }


    @Override
    /**
     * @validating admissionOptional by uuid
     * @Validate if the List of admissionOptional is empty otherwise return record not found
     * Create the admissionOptional definition and update
     * @return a Success Message
     * * */
    public ApiResponse<String> updateAdmission(UUID admissionUuid, AdmissionRequest request) {
        Admission admission = validateTreatmentByUuid(admissionUuid);

        if(request.getNoOfNights() != null){
            admission.setNoOfNights(request.getNoOfNights());
            admission.setAmount(admission.getRoom().getCost() * admission.getNoOfNights());
//            admission.setStatus("treatment completed");
//            admission.setDischargeDate(LocalDateTime.now());
        }

        admissionRepo.save(admission);
        return new ApiResponse<>(AppStatus.SUCCESS.label, "Record Updated Successfully");
    }

    @Override
    /**
     * @validating admissionOptional by uuid
     * @Validate if the List of admissionOptional is empty otherwise return record not found
     * Create the admissionOptional definition and update
     * @return a Success Message
     * * */
    public ApiResponse<String> deleteAdmission(UUID admissionUuid) {
        Admission admission = validateTreatmentByUuid(admissionUuid);
        admissionRepo.delete(admission);
        return new ApiResponse<>(AppStatus.SUCCESS.label, "Record deleted successfully");
    }


}
