package Salvation.Clinic.controller.TreatmentController;


import Salvation.Clinic.model.dto.request.TreatmentRequest.AdmissionRequest;
import Salvation.Clinic.model.dto.request.TreatmentRequest.TreatmentRequest;
import Salvation.Clinic.model.dto.response.ApiResponse;
import Salvation.Clinic.model.dto.response.TreatmentResponse.AdmissionResponse;
import Salvation.Clinic.model.dto.response.TreatmentResponse.TreatmentResponse;
import Salvation.Clinic.services.treatment.AdmissionService;
import Salvation.Clinic.services.treatment.TreatmentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.RecursiveTask;

import static Salvation.Clinic.EndPoints.EndpointParam.*;
import static Salvation.Clinic.EndPoints.TreatmentEndPoints.*;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(treatment)
@RestController
@RequiredArgsConstructor
public class TreatmentController {

    private final TreatmentService treatmentService;
    private final AdmissionService admissionService;


                                        //FIND_LISTS_OF_TREATMENT

    @GetMapping(FIND_TREATMENT)
    @ApiOperation(value = "EndPoint for finding the list of treatment", response = TreatmentResponse.class, responseContainer = "List")
    public ApiResponse<List<TreatmentResponse>> findListOfTreatment(@RequestParam(value = PAGE, defaultValue = PAGE_DEFAULT) int page,
                                                                    @RequestParam(value = SIZE, defaultValue = SIZE_DEFAULT) int size){
        return treatmentService.getListOfTreatment(page, size);
    }

    @GetMapping(FIND_ADMISSION)
    @ApiOperation(value = "EndPoint for finding the list of Admission", response = AdmissionResponse.class, responseContainer = "List")
    public ApiResponse <List<AdmissionResponse>> findListOfAdmission(@RequestParam(value = PAGE, defaultValue = PAGE_DEFAULT) int page,
                                                                     @RequestParam(value = PAGE, defaultValue = PAGE_DEFAULT) int size){
        return admissionService.getListOfAdmission(page, size);
    }


                                                    // ADD TREATMENT

    @PostMapping(ADD_TREATMENT)
    @ApiOperation(value = "Endpoint to add treatment to database", response = String.class )
    public ApiResponse<String> addTreatment(@Valid @RequestBody TreatmentRequest request) throws IOException {
        return treatmentService.addTreatment(request);
    }

    @PostMapping(ADD_ADMISSION)
    @ApiOperation(value = "Endpoint to add admission to database", response = String.class)
    public ApiResponse<String> addAdmission (@Valid @RequestBody AdmissionRequest request) throws IOException{
        return admissionService.addAdmission(request);
    }


                                                // UPDATE TREATMENT

    @PutMapping(UPDATE_TREATMENT)
    @ApiOperation(value = "Endpoint for updating treatment", response = String.class)
    public ApiResponse<String> updateTreatment(@PathVariable(value = "uuid") UUID treatmentUuid, @RequestBody TreatmentRequest request){
        return treatmentService.updateTreatment(treatmentUuid, request);
    }

    @PutMapping(UPDATE_ADMISSION)
    @ApiOperation(value = "Endpoint for updating admission", response = String.class)
    public ApiResponse<String> updateAdmission(@PathVariable(value = "uuid") UUID admissionUuid, @RequestBody AdmissionRequest request){
        return admissionService.updateAdmission(admissionUuid, request);
    }


                                                    // DELETE TREATMENT

    @DeleteMapping(DELETE_TREATMENT)
    @ApiOperation(value = "Endpoint for deleting treatment from database", response = String.class)
    public ApiResponse<String> deleteTreatment (@PathVariable (value = "uuid") UUID treatmentUuid){
        return treatmentService.deleteTreatment(treatmentUuid);
    }

    @DeleteMapping(DELETE_ADMISSION)
    @ApiOperation(value = "Endpoint for deleting admission in the database", response = String.class)
    public ApiResponse<String> deleteAdmission(@PathVariable(value = "uuid") UUID admissionUuid){
        return admissionService.deleteAdmission(admissionUuid);
    }



}
