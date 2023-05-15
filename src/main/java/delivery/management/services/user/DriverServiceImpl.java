package delivery.management.services.user;


import delivery.management.exception.RecordNotFoundException;
import delivery.management.mapper.Mapper;
import delivery.management.model.dto.enums.AppStatus;
import delivery.management.model.dto.request.userRequest.DriverRequest;
import delivery.management.model.dto.request.userRequest.changeDriverPasswordRequest;
import delivery.management.model.dto.request.userRequest.loginDriverRequest;
import delivery.management.model.dto.response.othersResponse.ApiResponse;
import delivery.management.model.dto.response.userResponse.CustomerResponse;
import delivery.management.model.dto.response.userResponse.DriverResponse;
import delivery.management.model.entity.user.Customer;
import delivery.management.model.entity.user.Driver;
import delivery.management.repo.user.DriverRepository;
import delivery.management.utills.EmailUtils;
import delivery.management.utills.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final EmailUtils emailUtils;


    @Override
    /**
     * @Finding the list of Driver
     * @Validate if the List of Driver is empty otherwise return record not found
     * @return the list of Driver and a Success Message
     * * */
    public ApiResponse<List<DriverResponse>> getListOfDriver(int page, int size) {

    List<Driver> driverList = driverRepository.findAll(PageRequest.of(page, size)).toList();

        if(driverList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(driverList, CustomerResponse.class));

    }


    /**
     * @Validating existingDriverOptional by name*
     * @Validate if the List of existingDriverOptional is empty otherwise return Duplicate Record*
     * * */
    private void validateDriverByEmailId(String email){
        Optional<Driver> existingDriverOptional = driverRepository.findByEmailId(email);
        if(existingDriverOptional.isPresent())
            throw new RecordNotFoundException("Duplicate record");
    }


    @Override
    /**
     * @Validate that no duplicate Driver is allowed
     * @Validate that Driver exists otherwise return record not found*
     * Create Driver definition and save
     * @return success message
     * * */
    public ApiResponse<String> addDriver(DriverRequest request) throws IOException {

        validateDriverByEmailId(request.getEmail());

        Driver driver = new Driver();

        driver.setName(request.getName());
        driver.setEmail(request.getEmail());
        driver.setAddress(request.getAddress());
        driver.setCountry(request.getCountry());
        driver.setCity(request.getCity());
        driver.setGender(request.getGender());
        driver.setPassword(request.getPassword());
        driver.setPhone(request.getPhone());
        driver.setUsername(request.getUsername());
        driver.setPhoto(request.getPhoto());
        driver.setNin(request.getNin());
        driver.setDriverLicence(request.getDriverLicence());

        driverRepository.save(driver);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record added Successfully");

    }



    @Override
    /**
     * @Finding the list of driverOptional by uuid*
     * @Validate if the List of driverOptional is empty otherwise return record not found
     * Create the driver definition and get the driver
     * @return the list of driver and a Success Message
     * * */
    public ApiResponse<DriverResponse> getDriverById(UUID driverId) {

        Optional<Driver> driverOptional = driverRepository.findByUuid(driverId);

        if(driverOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        Driver driver = driverOptional.get();

        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(driver,CustomerResponse.class));

    }

    /**
     * @validating driverOptional by uuid
     * @Validate if the List of driver is empty otherwise return record not found
     * @return driverOptional
     * * */
    private Driver validateDriver(UUID uuid){
        Optional<Driver> driverOptional = driverRepository.findByUuid(uuid);
        if(driverOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return driverOptional.get();
    }



    @Override
    /**
     * @validating driverOptional by uuid
     * @Validate if the List of driver is empty otherwise return record not found
     * Create the driver definition and update
     * @return a Success Message
     * * */
    public ApiResponse<String> updateDriver(UUID driverId, DriverRequest request) {

        Driver driver = validateDriver(driverId);

        driver.setName(request.getName());
        driver.setEmail(request.getEmail());
        driver.setAddress(request.getAddress());
        driver.setCountry(request.getCountry());
        driver.setCity(request.getCity());
        driver.setGender(request.getGender());
        driver.setPhone(request.getPhone());
        driver.setUsername(request.getUsername());
        driver.setPhoto(request.getPhoto());
        driver.setNin(request.getNin());

        driverRepository.save(driver);
        return new ApiResponse<String>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record Updated Successfully");

    }



    @Override
    /**
     * @validating driver by uuid
     * @Validate if driver is empty otherwise return record not found
     * @Delete driver
     * @return a Success Message
     * * */
    public ApiResponse<String> deleteDriver(UUID driverId) {

        Driver driver = validateDriver(driverId);
        driverRepository.delete(driver);
        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record Deleted successfully");

    }


    @Override
    /**
     * @Validating existingDriver by Email
     * @Validate driver password and change password
     * @Save the new password
     * @Return a Success Message if oldPassword is correct
     * @Return a Failed Message if oldPassword is Incorrect
     */
    public ApiResponse<String> resetDriverPassword(String email, changeDriverPasswordRequest request) {

        Driver driver = driverRepository.findByEmail(email);

        if(driver.getPassword().equals(request.getOldPassword())){
            driver.setPassword(request.getNewPassword());
            driverRepository.save(driver);

            return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    "Password Changed successfully");
        }
        return new ApiResponse(AppStatus.FAILED.label, HttpStatus.BAD_REQUEST.value(),
                "Incorrect Old Password");

    }


    @Override
    /**
     * @Validating existingDriver by Email
     * @Getting email and password of Driver and sending it to Driver`s Email
     * @Return a Success Message
     */
    public ApiResponse<String> forgotDriverPassword(String email) throws MessagingException {

        Driver driver = driverRepository.findByEmail(email);

        emailUtils.forgotMail(driver.getEmail(), "Credentials by Delivery Management System", driver.getPassword() );

        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Check your email for credentials");

    }

    @Override
    /**
     * @Validating and finding existingDriver by Email
     * @Validate driver email and password
     * @Return a Success Message if email and password is correct
     * @Return a Failed Message if email and password is Incorrect
     */
    public ApiResponse<String> loginDriver(String email, loginDriverRequest request) {

        Driver driver = driverRepository.findByEmail(email);

        if (driver.getEmail().equals(request.getEmail())
                && driver.getPassword().equals(request.getPassword())) {

            return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    "Client login successfully");
        }

        return new ApiResponse(AppStatus.FAILED.label, HttpStatus.BAD_REQUEST.value(),
                "Incorrect Email or Password");

    }


}
