package Salvation.Clinic.services.drugs;

import Salvation.Clinic.mapper.Mapper;
import Salvation.Clinic.model.dto.enums.AppStatus;
import Salvation.Clinic.model.dto.request.DrugRequest.DrugRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.DrugResponse.DrugResponse;
import Salvation.Clinic.model.dto.response.exception.RecordNotFoundException;
import Salvation.Clinic.model.entity.drug.DrugCategory;
import Salvation.Clinic.model.entity.drug.Drugs;
import Salvation.Clinic.model.entity.users.Users;
import Salvation.Clinic.repo.drugRepo.DrugCategoryRepo;
import Salvation.Clinic.repo.drugRepo.DrugRepo;
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
public class DrugServiceImpl implements DrugService{

    private final UserRepo userRepo;
    private final DrugRepo drugRepo;
    private final DrugCategoryRepo drugCategoryRepo;


    @Override
    /**
     * @Finding the list of all drugs
     * @Validate if the List of drugs is empty otherwise return record not found*
     * @return the list of drugs and a Success Message* *
     * * */
    public ApiResponse<List<DrugResponse>> getListOfDrug(int page, int size) {
        List<Drugs> drugsList = drugRepo.findAll(PageRequest.of(page, size)).toList();

        if(drugsList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, Mapper.convertList(drugsList, DrugResponse.class));

    }


    private void validateDuplicateDrug(String name){
        Optional<Drugs> drugsOptional = drugRepo.findByName(name);
        if(drugsOptional.isPresent())
            throw new RecordNotFoundException("Duplicate Record");
    }


    @Override
    /**
     * @Validate that no duplicate drugs allow
     * @Validate that drugs category exists otherwise return record not found*
     * @Validate that user creating the drugs exists, otherwise return user not found*
     * Create the drugs definition and save
     * @return success message* *
     * * */
    public ApiResponse<String> addDrug(DrugRequest request) {
        validateDuplicateDrug(request.getName());

        Users existingUsers = userRepo.findByUuid(request.getCreatedById())
            .orElseThrow( () -> new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        DrugCategory existingDrugCategory = drugCategoryRepo.findByUuid(request.getDrugCategoryId())
                .orElseThrow( () -> new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        Drugs drugs = new Drugs();

        drugs.setName(request.getName());
        drugs.setBrand(request.getBrand());
        drugs.setDrugCategory(existingDrugCategory);
        drugs.setDescription(request.getDescription());
        drugs.setCategory(existingDrugCategory.getName());
        drugs.setPostedBy(existingUsers.getName());
        drugs.setExpDate(request.getExpDate());
        drugs.setStatus("good");
        drugs.setQuantity(request.getQuantity());
        drugs.setSalesPrice(request.getSalesPrice());
        drugs.setPurchasePrice(request.getPurchasedPrice());
        drugs.setUsers(existingUsers);

        drugRepo.save(drugs);

        return new ApiResponse<>(AppStatus.SUCCESS.label, "Record Added Successfully");

    }

    /**
     * @validating drugOptional by uuid
     * @Validate if the List of drugOptional is empty otherwise return record not found
     * @return drugOptional
     * * */
    public Drugs validateDrugByUuid(UUID uuid){
        Optional<Drugs> drugsOptional = drugRepo.findByUuid(uuid);
        if(drugsOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

            return drugsOptional.get();
    }


    @Override
    /**
     * @validating drugOptional by uuid
     * @Validate if the List of user is empty otherwise return record not found
     * Create the drug definition and update
     * @return a Success Message
     * * */
    public ApiResponse<String> updateDrug(UUID drugUuid, DrugRequest request) {
        Drugs drugs = validateDrugByUuid(drugUuid);

        if(request.getName() != null){
            drugs.setName(request.getName());
        }
        if(request.getCategory() != null){
            drugs.setCategory(request.getCategory());
        }
        if(request.getPurchasedPrice() != null){
            drugs.setPurchasePrice(request.getPurchasedPrice());
        }
        if(request.getSalesPrice() != null){
            drugs.setSalesPrice(request.getSalesPrice());
        }
        if(request.getBrand() != null){
            drugs.setBrand(request.getBrand());
        }
        if(request.getQuantity() != null){
            drugs.setQuantity(request.getQuantity());
        }
        if(request.getDescription() != null){
            drugs.setDescription(request.getDescription());
        }
        if(request.getExpDate() != null){
            drugs.setExpDate(request.getExpDate());
        }

        drugRepo.save(drugs);

        return new ApiResponse<>(AppStatus.SUCCESS.label, "Record updated Successfully");

    }



    @Override
    /**
     * @validating drug by uuid
     * @Validate if drug is empty otherwise return record not found
     * @Delete drug
     * @return a Success Message
     * * */
    public ApiResponse<String> deleteDrug(UUID drugUuid) {
        Drugs drugs = validateDrugByUuid(drugUuid);
        drugRepo.delete(drugs);
        return new ApiResponse<>(AppStatus.SUCCESS.label,  "Record Deleted Successfully");
    }



}
