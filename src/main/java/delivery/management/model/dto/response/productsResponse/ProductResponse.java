package delivery.management.model.dto.response.productsResponse;


import delivery.management.model.dto.BaseDto;
import lombok.Data;

@Data
public class ProductResponse {

     private String productName;
     private Integer quantity;
     private String model;
     private String colour;
     private Integer weight;
     private String photo;
     private String status;
     private Double amount;
     private String description;


}
