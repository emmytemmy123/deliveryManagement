package delivery.management.model.dto.request.productsRequest;

import delivery.management.utills.MessageUtil;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ProductItemsRequest {

     @NotNull(message = MessageUtil.INVALID_NAME)
     @NotEmpty(message = MessageUtil.INVALID_NAME)
     private String name;

     @NotNull(message = MessageUtil.INVALID_NAME)
     @Min(value = 1, message = MessageUtil.INVALID_TYPE)
     private Integer quantity;

//     @NotNull(message = MessageUtil.INVALID_NAME)
//     @NotEmpty(message = MessageUtil.INVALID_NAME)
     private String model;

     @NotNull(message = MessageUtil.INVALID_NAME)
     @NotEmpty(message = MessageUtil.INVALID_NAME)
     private String colour;

     @NotNull(message = MessageUtil.INVALID_NAME)
     @Min(value = 1, message = MessageUtil.INVALID_TYPE)
     private Integer weight;

//     @NotNull(message = MessageUtil.INVALID_NAME)
//     @NotEmpty(message = MessageUtil.INVALID_NAME)
     private String photo;

     @NotNull(message = MessageUtil.INVALID_NAME)
     @NotEmpty(message = MessageUtil.INVALID_NAME)
     private String description;

     //@NotNull(message = MessageUtil.INVALID_NAME)
//     @NotEmpty(message = MessageUtil.INVALID_NAME)
//     private String sendBy;





}
