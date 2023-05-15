package delivery.management.model.dto.response.othersResponse;


import delivery.management.model.dto.BaseDto;
import lombok.Data;

@Data
public class DocumentResponse extends BaseDto {

     private String file_type;
     private String file_name;
     private String file_size;
     private String description;
     private String record_id;

}
