package delivery.management.services.user;

import delivery.management.mapper.Mapper;
import delivery.management.model.dto.enums.AppStatus;
import delivery.management.model.dto.request.userRequest.SenderRequest;
import delivery.management.model.dto.request.userRequest.changeSenderPasswordRequest;
import delivery.management.model.dto.request.userRequest.loginSenderRequest;
import delivery.management.model.dto.response.othersResponse.ApiResponse;
import delivery.management.model.entity.user.AppUser;
import delivery.management.model.entity.user.Customer;
import delivery.management.model.entity.user.Driver;
import delivery.management.model.entity.user.Sender;
import delivery.management.repo.user.SenderRepository;
import delivery.management.repo.user.UserRepository;
import delivery.management.model.dto.response.userResponse.SenderResponse;
import delivery.management.utills.MessageUtil;
import delivery.management.exception.RecordNotFoundException;
import delivery.management.utills.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SenderServiceImpl implements SenderService {

    private  final SenderRepository senderRepository;
    private final UserRepository userRepository;
    private final EmailUtils emailUtils;


    @Override
    /**
     * @Finding the list of Sender
     * @Validate if the List of Sender is empty otherwise return record not found
     * @return the list of Sender and a Success Message
     * * */
    public ApiResponse<List<SenderResponse>> getListOfSender(int page, int size) {

            List<Sender> senderList = senderRepository.findAll(PageRequest.of(page,size)).toList();
            if(senderList.isEmpty())
                throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

            return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    Mapper.convertList(senderList, SenderResponse.class));
        }


    /**
     * Set and get the Sender parameters
     */
    private Sender getSenderFromRequest(SenderRequest request){

        Sender sender = new Sender();

        sender.setName(request.getName());
        sender.setEmail(request.getEmail());
        sender.setAddress(request.getAddress());
        sender.setCountry(request.getCountry());
        sender.setCity(request.getCity());
        sender.setGender(request.getGender());
        sender.setPassword(request.getPassword());
        sender.setPhone(request.getPhone());
        sender.setUsername(request.getUsername());
        sender.setPhoto(request.getPhoto());
        sender.setNin(request.getNin());

        return sender;
    }



    @Override
    /**
     * @Validate that no duplicate Sender is allowed
     * @Validate that Sender exists otherwise return record not found
     * Create Sender definition and save
     * @return success message
     * * */
    public ApiResponse<String> addSender(SenderRequest request) {
            Optional<Sender> senderOptional  = validateSenderByEmailId(request.getEmail());

            if(!senderOptional.isEmpty()){
                return new ApiResponse(AppStatus.FAILED.label, HttpStatus.EXPECTATION_FAILED.value(),
                        "Email Already Exist");
            }

            senderRepository.save(getSenderFromRequest(request));

            return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    "Record added Successfully");
        }


    @Override
    /**
     * @Finding the list of SenderOptional by uuid
     * @Validate if the List of SenderOptional is empty otherwise return record not found
     * Create the Sender definition and get the employee
     * @return the list of Sender and a Success Message
     * * */
    public ApiResponse<SenderResponse> getSenderById(UUID senderId) {

        Optional<Sender> senderOptional = senderRepository.findByUuid(senderId);

        if(senderOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        Sender sender = senderOptional.get();

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(sender, SenderResponse.class));

    }

    /**
     * @Validating existingSenderOptional by Email
     * @Validate if existingSenderOptional is empty otherwise return Duplicate Record
     * return existingSenderOptional
     * * */
    private Optional<Sender> validateSenderByEmailId(String email) {
        Optional<Sender> existingSenderOptional = senderRepository.findByEmailId(email);
        return existingSenderOptional;
    }

    /**
     * @validating SenderOptional by uuid*
     * @Validate if the List of Sender is empty otherwise return record not found
     * @return SenderOptional
     * * */
    private Sender validateSender(UUID uuid) {
        Optional<Sender> senderOptional = senderRepository.findByUuid(uuid);
        if (senderOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return senderOptional.get();
    }




    @Override
    /**
     * @validating SenderOptional by uuid
     * @Validate if the List of Sender is empty otherwise return record not found
     * Create the Sender definition and update
     * @return a Success Message
     * * */
    public ApiResponse<String> updateSender(UUID senderId, SenderRequest request) {

            Sender sender = validateSender(senderId);

            sender.setName(request.getName());
            sender.setEmail(request.getEmail());
            sender.setGender(request.getGender());
            sender.setCountry(request.getCountry());
            sender.setCity(request.getCity());
            sender.setAddress(request.getAddress());
            sender.setPhone(request.getPhone());
            sender.setUsername(request.getUsername());

            senderRepository.save(sender);
            return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    "Record Updated Successfully");
        }


    @Override
    /**
     * @validating Sender by uuid
     * @Validate if Sender is empty otherwise return record not found
     * @Delete Sender
     * @return a Success Message
     * * */
    public ApiResponse<String> deleteSender(UUID senderId) {
        Sender sender = validateSender(senderId);
        senderRepository.delete(sender);
        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    "Record Deleted successfully");
        }



    @Override
    /**
     * @Validating existingSender by Email
     * @Validate Sender password and change password
     * @Save the new password
     * @Return a Success Message if oldPassword is correct
     * @Return a Failed Message if oldPassword is Incorrect
     */
    public ApiResponse<String> resetSenderPassword(String email, changeSenderPasswordRequest request) {
        Sender sender = senderRepository.findByEmail(email);

        if(sender.getPassword().equals(request.getOldPassword())){
            sender.setPassword(request.getNewPassword());
            senderRepository.save(sender);

            return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    "Password Changed successfully");
        }
        return new ApiResponse(AppStatus.FAILED.label, HttpStatus.BAD_REQUEST.value(),
                "Incorrect Old Password");
    }


    @Override
    /**
     * @Finding Sender by Email
     * @Getting the value of email and password of Sender and sending it to Sender`s Email
     * @Return a Success Message
     */
    public ApiResponse<String> forgotSenderPassword(String email) throws MessagingException {
        Sender sender = senderRepository.findByEmail(email);

        emailUtils.forgotMail(sender.getEmail(), "Credentials by Delivery Management System", sender.getPassword() );

        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Check your email for credentials");
    }


    @Override
    /**
     * @Finding existingSender by Email
     * @Validate Sender email and password
     * @Return a Success Message if email and password is correct
     * @Return a Failed Message if email and password is Incorrect
     */
    public ApiResponse<String> loginSender(String email, loginSenderRequest request) {
        Sender sender = senderRepository.findByEmail(email);

        if (sender.getEmail().equals(request.getEmail())
                && sender.getPassword().equals(request.getPassword())) {

            return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    "Sender login successfully");
        }

        return new ApiResponse(AppStatus.FAILED.label, HttpStatus.BAD_REQUEST.value(),
                "Incorrect Email or Password");
    }


}
