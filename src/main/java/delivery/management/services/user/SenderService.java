package delivery.management.services.user;

import delivery.management.model.dto.request.userRequest.SenderRequest;
import delivery.management.model.dto.request.userRequest.changeSenderPasswordRequest;
import delivery.management.model.dto.request.userRequest.loginSenderRequest;
import delivery.management.model.dto.response.othersResponse.ApiResponse;
import delivery.management.model.dto.response.userResponse.SenderResponse;

import javax.mail.MessagingException;
import java.util.List;
import java.util.UUID;

public interface SenderService {


   ApiResponse<List<SenderResponse>> getListOfSender(int page, int size);

    ApiResponse<String> addSender(SenderRequest request);

    ApiResponse<SenderResponse> getSenderById(UUID senderId);

    ApiResponse<String> updateSender(UUID senderId, SenderRequest request);

    ApiResponse<String> deleteSender(UUID senderId);

    ApiResponse<String> resetSenderPassword(String email, changeSenderPasswordRequest request);

    ApiResponse<String> forgotSenderPassword(String email) throws MessagingException;

    ApiResponse<String> loginSender(String email, loginSenderRequest request);




}
