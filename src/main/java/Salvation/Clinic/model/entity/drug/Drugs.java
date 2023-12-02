package Salvation.Clinic.model.entity.drug;


import Salvation.Clinic.model.entity.BaseEntity;
import Salvation.Clinic.model.entity.users.Users;
import Salvation.Clinic.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@EntityListeners(BaseListener.class)
@Data
@AllArgsConstructor
@Table(name = "drugs")
public class Drugs extends BaseEntity {

    private String category;
    private String brand;
    private String name;
    private String status;
    private String description;
    private Integer quantity;
    private Double salesPrice;
    private Double purchasePrice;
    private Date expDate;
    private String postedBy;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "drugCategoryId", updatable = true)
    private DrugCategory drugCategory;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "drugPurchaseId", updatable = true)
    private DrugPurchase drugPurchase;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", updatable = true)
    private Users users;


    public Drugs(){

    }

}
