package Salvation.Clinic.controller.DrugController;


import Salvation.Clinic.model.dto.request.DrugRequest.DrugCategoryRequest;
import Salvation.Clinic.model.dto.request.DrugRequest.DrugRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.DrugResponse.DrugCategoryResponse;
import Salvation.Clinic.model.dto.response.DrugResponse.DrugResponse;
import Salvation.Clinic.services.drugs.DrugCategoryService;
import Salvation.Clinic.services.drugs.DrugService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static Salvation.Clinic.EndPoints.DrugEndPoints.*;
import static Salvation.Clinic.EndPoints.EndpointParam.*;

@CrossOrigin(origins= "http://localHost:4200")
@RestController
@RequestMapping(drug)
@RequiredArgsConstructor
public class DrugController {
    private final DrugCategoryService drugCategoryService;
    private final DrugService drugService;

                                                     // FIND THE LIST OF DRUG

    @GetMapping(FIND_DRUG_CATEGORY)
    @ApiOperation(value = "Endpoint for finding the list of drugCategory", response = DrugCategoryResponse.class, responseContainer = "List")
    public ApiResponse<List<DrugCategoryResponse>> findListOfDrugCategory(@RequestParam(value = PAGE, defaultValue = PAGE_DEFAULT) int page,
                                                                          @RequestParam(value = SIZE, defaultValue = SIZE_DEFAULT) int size){
        return drugCategoryService.getListOfDrugCategory(page, size);
    }

    @GetMapping(FIND_DRUG)
    @ApiOperation(value = "Endpoint for finding the list of drug", response = DrugResponse.class, responseContainer = "List")
    public ApiResponse<List<DrugResponse>> listOfDrug(@RequestParam(value = PAGE, defaultValue = PAGE_DEFAULT) int page,
                                                              @RequestParam(value = SIZE, defaultValue = PAGE_DEFAULT) int size){
        return drugService.getListOfDrug(page, size);
    }


                                                    // ADDING DRUG TO DATABASE

    @PostMapping(ADD_DRUG_CATEGORY)
    @ApiOperation(value = "Endpoint for adding drugCategory to database", response = String.class)
    public ApiResponse<String> addDrugCategory(@Valid @RequestBody DrugCategoryRequest request){
        return drugCategoryService.addDrugCategory(request);
    }

    @PostMapping(ADD_DRUG)
    @ApiOperation(value = "Endpoint for adding drug to database", response = String.class)
    public ApiResponse<String> addDrug(@Valid @RequestBody DrugRequest request){

        return drugService.addDrug(request);
    }


                                                // UPDATE DRUG IN DATABASE

    @PutMapping(UPDATE_DRUG)
    @ApiOperation(value = "Endpoint for updating drug in the database", response = String.class)
    public ApiResponse<String> updateDrug(UUID drugUuid, @RequestBody DrugRequest request){
        return drugService.updateDrug(drugUuid, request);
    }


                                        //DELETE DRUG FROM DATABASE

    @DeleteMapping(DELETE_DRUG)
    @ApiOperation(value = "Endpoint for deleting drug", response = String.class)
    public ApiResponse<String> deleteDrug(UUID drugUuid){
        return drugService.deleteDrug(drugUuid);
    }



}
