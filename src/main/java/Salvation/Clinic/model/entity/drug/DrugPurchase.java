package Salvation.Clinic.model.entity.drug;


import Salvation.Clinic.model.entity.BaseEntity;
import Salvation.Clinic.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@EntityListeners(BaseListener.class)
@AllArgsConstructor
@Table(name = "drugPurchase")
public class DrugPurchase extends BaseEntity {

    private String name;
    private String category;
    private String description;
    private String companyName;
    private String brand;
    private Integer quantity;
    private Double price;
    private LocalDateTime productPurchaseDate;
    private String postedBy;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "drugCategoryId", updatable = true)
    private DrugCategory drugCategory;


    public DrugPurchase(){

    }

}
