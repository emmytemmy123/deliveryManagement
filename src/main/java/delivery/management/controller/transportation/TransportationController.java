package delivery.management.controller.transportation;


import delivery.management.dto.ApiResponse;
import delivery.management.model.dto.request.transportationRequest.VehicleCategoryRequest;
import delivery.management.model.dto.request.transportationRequest.VehicleRequest;
import delivery.management.model.dto.response.transportationResponse.VehicleCategoryResponse;
import delivery.management.model.dto.response.transportationResponse.VehicleResponse;
import delivery.management.services.Transportation.VehicleCategoryService;
import delivery.management.services.Transportation.VehicleService;
import delivery.management.utills.EndpointParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static delivery.management.utills.EndPoints.TransportationEndPoints.*;


@RestController
@RequestMapping(transportation)
@RequiredArgsConstructor
public class TransportationController {

    private final VehicleCategoryService vehicleCategoryService;
    private final VehicleService vehicleService;



                                //FIND_LISTS_OF_TRANSPORTATION

    @GetMapping(FIND_VEHICLE_CATEGORY)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') ")
    @ApiOperation(value = "Endpoint for retrieving lists of vehicleCategory", response = VehicleCategoryResponse.class, responseContainer = "List")
    public delivery.management.dto.ApiResponse<List<VehicleCategoryResponse>> getListOfVehicleCategoryResponse(@RequestParam(value = EndpointParam.PAGE, defaultValue = EndpointParam.PAGE_DEFAULT) int page,
                                                                                                               @RequestParam(value = EndpointParam.SIZE, defaultValue = EndpointParam.SIZE_DEFAULT) int size) {
        return vehicleCategoryService.getListOfVehicleCategory(page, size);
    }

    @GetMapping(FIND_VEHICLE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for retrieving lists of vehicle", response = VehicleResponse.class, responseContainer = "List")
    public delivery.management.dto.ApiResponse<List<VehicleResponse>> getListOfVehicle(@RequestParam(value = EndpointParam.PAGE, defaultValue = EndpointParam.PAGE_DEFAULT) int page,
                                                                                       @RequestParam(value = EndpointParam.SIZE, defaultValue = EndpointParam.SIZE_DEFAULT) int size) {
        return vehicleService.getListOfVehicle(page, size);
    }



                                        //ADD_TRANSPORTATION

    @PostMapping(ADD_VEHICLE_CATEGORY)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for adding new vehicleCategory to database", response = String.class)
    public delivery.management.dto.ApiResponse<String> addVehicleCategory(@Valid @RequestBody VehicleCategoryRequest request) {
        return vehicleCategoryService.addVehicleCategory(request);
    }


    @PostMapping(ADD_VEHICLE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for adding new vehicle to database", response = String.class)
    public delivery.management.dto.ApiResponse<String> addVehicle(@Valid @RequestBody VehicleRequest request) {
        return vehicleService.addVehicle(request);
    }


                                        //FIND_TRANSPORTATION_BY_ID

    @GetMapping(FIND_VEHICLE_CATEGORY_BY_ID)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching vehicleCategory by id from database", response = VehicleCategoryResponse.class)
    public delivery.management.dto.ApiResponse<VehicleCategoryResponse> getVehicleCategoryResponseById(@RequestParam UUID deliveryId) {
        return vehicleCategoryService.getVehicleCategoryById(deliveryId);
    }

    @GetMapping(FIND_VEHICLE_BY_ID)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching vehicle by id from database", response = VehicleResponse.class)
    public delivery.management.dto.ApiResponse<VehicleResponse> getVehicleResponseById(@RequestParam UUID deliveryId) {
        return vehicleService.getVehicleById(deliveryId);
    }



                                            //UPDATE_TRANSPORTATION

    @PutMapping(UPDATE_VEHICLE_CATEGORY)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for updating vehicleCategory by id from database", response = String.class)
    public delivery.management.dto.ApiResponse<String> updateVehicleCategory(@PathVariable(value = "id")  UUID vehicleCategoryUuid,
                                                                             @RequestBody VehicleCategoryRequest request) {
        return vehicleCategoryService.updateVehicleCategory(vehicleCategoryUuid, request);
    }

    @PutMapping(UPDATE_VEHICLE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for updating vehicle by id from database", response = String.class)
    public delivery.management.dto.ApiResponse<String> updateVehicle(@PathVariable(value = "id")  UUID vehicleUuid,
                                                                     @RequestBody VehicleRequest request) {
        return vehicleService.updateVehicle(vehicleUuid, request);
    }


                                                //DELETE_TRANSPORTATION

    @DeleteMapping(DELETE_VEHICLE_CATEGORY)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') ")
    @ApiOperation(value = "Endpoint for deleting vehicleCategory by id from database", response = String.class)
    public delivery.management.dto.ApiResponse<String> deleteVehicleCategory(@PathVariable(value = "id") UUID vehicleCategoryId) {
        return vehicleCategoryService.deleteVehicleCategory(vehicleCategoryId);
    }


    @DeleteMapping(DELETE_VEHICLE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') ")
    @ApiOperation(value = "Endpoint for deleting vehicle by id from database", response = String.class)
    public ApiResponse<String> deleteVehicle(@PathVariable(value = "id") UUID vehicleId) {
        return vehicleService.deleteVehicle(vehicleId);
    }





}
