package delivery.management.model.dto.response.userResponse;


import delivery.management.model.dto.BaseDto;
import lombok.Data;

@Data
public class UsersResponse extends BaseDto {

     private String name;
     private String email;
     private String phone;
     private String address;
     private String photo;
     private String gender;
     private String country;
     private String city;
     private String username;
     private String nin;


}
