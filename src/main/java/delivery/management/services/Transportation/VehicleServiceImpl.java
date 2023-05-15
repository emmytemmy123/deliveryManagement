package delivery.management.services.Transportation;


import delivery.management.dto.ApiResponse;
import delivery.management.exception.RecordNotFoundException;
import delivery.management.mapper.Mapper;
import delivery.management.model.dto.enums.AppStatus;
import delivery.management.model.dto.request.transportationRequest.VehicleRequest;
import delivery.management.model.dto.response.transportationResponse.VehicleResponse;
import delivery.management.model.entity.transportation.Vehicle;
import delivery.management.model.entity.transportation.VehicleCategory;
import delivery.management.model.entity.user.Driver;
import delivery.management.repo.transportation.VehicleCategoryRepository;
import delivery.management.repo.transportation.VehicleRepository;
import delivery.management.repo.user.DriverRepository;
import delivery.management.utills.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleCategoryRepository vehicleCategoryRepository;
    private final DriverRepository driverRepository;



    @Override
    /**
     * @Finding the list of Vehicle
     * @Validate if the List of Vehicle is empty otherwise return record not found
     * @return the list of Vehicle and a Success Message
     * * */
    public ApiResponse<List<VehicleResponse>> getListOfVehicle(int page, int size) {

        List<Vehicle> vehicleList = vehicleRepository.findAll(PageRequest.of(page,size)).toList();

        if(vehicleList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

       return new ApiResponse(AppStatus.SUCCESS.label, Mapper.convertList(vehicleList,VehicleResponse.class));

    }


    /**
     * @Validating existingVehicleOptional by plateNo
     * @Validate if the List of existingVehicleOptional is empty otherwise return Duplicate Record
     * */
    private void validateDuplicateVehicleByPlateNo(String plateNo) {
        Optional<Vehicle> existingVehicleOptional = vehicleRepository.findVehicleByPlateNo(plateNo);
        if(existingVehicleOptional.isPresent())
            throw new RecordNotFoundException("Duplicate record");
    }


    @Override
    /**
     * @Validate that no duplicate vehicle is allowed
     * @Validate that driver exists otherwise return record not found
     * Create the vehicle definition and save
     * @return success message
     * * */
    public ApiResponse<String> addVehicle(VehicleRequest request) {

        validateDuplicateVehicleByPlateNo(request.getPlateNo());

        Driver existingDriver = driverRepository.findByUuid(request.getDriverId())
                .orElseThrow(()->new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        VehicleCategory existingVehicleCategory = vehicleCategoryRepository.findByUuid(request.getVehicleCategoryId())
                .orElseThrow(()->new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));


        Vehicle vehicle = new Vehicle();

        vehicle.setBrand(request.getBrand());
        vehicle.setModel(request.getModel());
        vehicle.setPlateNo(request.getPlateNo());
        vehicle.setColour(request.getColour());
        vehicle.setDriver(existingDriver.getName());
        vehicle.setVehicleCategory(existingVehicleCategory);

        vehicleRepository.save(vehicle);

        return new ApiResponse<>(AppStatus.SUCCESS.label, "Record created successfully");

    }



    @Override
    /**
     * @Finding the list of all vehicleOptional by uuid*
     * @Validate if the List of vehicleOptional is empty otherwise return record not found
     * Create the vehicle definition and get the product Optional by uuid
     * @return the list of vehicle and a Success Message* *
     * * */
    public ApiResponse<VehicleResponse> getVehicleById(UUID vehicleId) {

        Optional<Vehicle> vehicleOptional = vehicleRepository.findByUuid(vehicleId);

        if(vehicleOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        Vehicle vehicle = vehicleOptional.get();

        return new ApiResponse<VehicleResponse>(AppStatus.SUCCESS.label,
                Mapper.convertObject(vehicle,VehicleResponse.class));


    }


    /**
     * @validating vehicle by uuid
     * @Validate if vehicle is empty otherwise return record not found
     * @return vehicle
     * * */
    private Vehicle validateVehicle(UUID uuid){
        return vehicleRepository.findByUuid(uuid)
                .orElseThrow(()->new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));
    }


    @Override
    /**
     * @Validating the list of existingVehicle by uuid
     * @Validate if the List of existingVehicle is empty otherwise return record not found
     * Create the vehicle definition and save
     * @return a Success Message
     * * */
    public ApiResponse<String> updateVehicle(UUID vehicleId, VehicleRequest request) {

    Vehicle vehicle = validateVehicle(vehicleId);

        vehicle.setBrand(request.getBrand());
        vehicle.setModel(request.getModel());
        vehicle.setPlateNo(request.getPlateNo());
        vehicle.setColour(request.getColour());

        vehicleRepository.save(vehicle);

        return new ApiResponse<>(AppStatus.SUCCESS.label, "Record created successfully");

    }


    @Override
    /**
     * @validating vehicle by uuid*
     * @Validate if vehicle is empty otherwise return record not found
     * @Delete vehicle
     * @return a Success Message
     * * */
    public ApiResponse<String> deleteVehicle(UUID vehicleId) {

        Vehicle vehicle = validateVehicle(vehicleId);
        vehicleRepository.delete(vehicle);
        return new ApiResponse<>(AppStatus.SUCCESS.label, "Record Deleted Successfully");

    }


}
