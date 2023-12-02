package Salvation.Clinic.services.drugs;


import Salvation.Clinic.mapper.Mapper;
import Salvation.Clinic.model.dto.enums.AppStatus;
import Salvation.Clinic.model.dto.request.DrugRequest.DrugPurchaseRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.DrugResponse.DrugPurchaseResponse;
import Salvation.Clinic.model.dto.response.exception.RecordNotFoundException;
import Salvation.Clinic.model.entity.drug.DrugCategory;
import Salvation.Clinic.model.entity.drug.DrugPurchase;
import Salvation.Clinic.model.entity.users.Users;
import Salvation.Clinic.repo.drugRepo.DrugCategoryRepo;
import Salvation.Clinic.repo.drugRepo.DrugPurchaseRepo;
import Salvation.Clinic.repo.drugRepo.DrugRepo;
import Salvation.Clinic.repo.userRepo.UserRepo;
import Salvation.Clinic.utills.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DrugPurchaseServiceImpl implements DrugPurchaseService{

    private final DrugPurchaseRepo drugPurchaseRepo;
    private final DrugRepo drugRepo;
    private DrugCategoryRepo drugCategoryRepo;
    private UserRepo userRepo;


    @Override
    /**
     * @Finding the list of all drugPurchase
     * @Validate if the List of drugPurchase is empty otherwise return record not found*
     * @return the list of drugPurchase and a Success Message* *
     * * */
    public ApiResponse<List<DrugPurchaseResponse>> getListOfDrugPurchase(int page, int size) {
        List<DrugPurchase> drugPurchaseList = drugPurchaseRepo.findAll(PageRequest.of(page, size)).toList();
        if(drugPurchaseList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label,
                Mapper.convertList(drugPurchaseList, DrugPurchaseResponse.class));


    }


    public void validateDuplicateDrugByName(String name){
        Optional<DrugPurchase> drugPurchase = drugPurchaseRepo.findByName(name);
        if(drugPurchase.isPresent())
            throw new RecordNotFoundException("Duplicate Record");
    }

    @Override
    /**
     * @Validate that no duplicate drugPurchase is allowed
     * @Validate that drugs category exists otherwise return record not found*
     * @Validate that user creating the drugs exists, otherwise return user not found*
     * Create the drugPurchase definition and save
     * @return success message* *
     * * */
    public ApiResponse<String> addDrugCategory(DrugPurchaseRequest request) {
        validateDuplicateDrugByName(request.getName());

        DrugCategory existingDrugCategory = drugCategoryRepo.findByUuid(request.getDrugCategoryId())
                .orElseThrow( () -> new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        Users existingUsers = userRepo.findByUuid(request.getCreatedById())
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        DrugPurchase drugPurchase =  new DrugPurchase();

        drugPurchase.setName(request.getName());
        drugPurchase.setBrand(request.getBrand());
        drugPurchase.setPrice(request.getPrice());
        drugPurchase.setQuantity(request.getQuantity());
        drugPurchase.setCompanyName(request.getCompanyName());
        drugPurchase.setProductPurchaseDate(LocalDateTime.now());
        drugPurchase.setCategory(existingDrugCategory.getName());
        drugPurchase.setDrugCategory(existingDrugCategory);
        drugPurchase.setDescription(request.getDescription());
        drugPurchase.setPostedBy(existingUsers.getName());

        drugPurchaseRepo.save(drugPurchase);

        return new ApiResponse<>(AppStatus.SUCCESS.label, "Record Added Successfully");

    }


    public DrugPurchase validateDrugPurchaseByUuid(UUID uuid){
    Optional<DrugPurchase> drugPurchaseOptional = drugPurchaseRepo.findByUuid(uuid);
    if(drugPurchaseOptional.isEmpty())
        throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
    return drugPurchaseOptional.get();
    }


    @Override
    /**
     * @validating drugPurchase by uuid
     * @Validate if the List of drugPurchase is empty otherwise return record not found
     * Create the drugPurchase definition and update
     * @return a Success Message
     * * */
    public ApiResponse<String> updateDrugPurchase(UUID drugPurchaseUuid, DrugPurchaseRequest request) {
       DrugPurchase drugPurchase = validateDrugPurchaseByUuid(drugPurchaseUuid);

       if(request.getName() !=null){
           drugPurchase.setName(request.getName());
       }
        if(request.getCategory() !=null){
            drugPurchase.setCategory(request.getCategory());
        }
        if(request.getDescription() !=null){
            drugPurchase.setDescription(request.getDescription());
        }
        if(request.getCompanyName() !=null){
            drugPurchase.setCompanyName(request.getCompanyName());
        }
        if(request.getBrand() !=null){
            drugPurchase.setBrand(request.getBrand());
        }
        if(request.getQuantity() !=null){
            drugPurchase.setQuantity(request.getQuantity());
        }
        if(request.getPrice() !=null){
            drugPurchase.setPrice(request.getPrice());
        }

        drugPurchaseRepo.save(drugPurchase);

        return new ApiResponse<>(AppStatus.SUCCESS.label, "Record updated Successfully");

    }

    @Override
    /**
     * @validate DrugPurchase by uuid
     * @Validate if DrugPurchase is empty otherwise return record not found
     * @Delete DrugPurchase
     * @return a Success Message
     * * */
    public ApiResponse<String> deleteDrugPurchase(UUID drugPurchaseUuid) {
    DrugPurchase drugPurchase = validateDrugPurchaseByUuid(drugPurchaseUuid);
    drugPurchaseRepo.delete(drugPurchase);
    return new ApiResponse<>(AppStatus.SUCCESS.label, "Record Deleted Successfully");
    }



}
