package Salvation.Clinic.model.entity.transaction;


import Salvation.Clinic.model.entity.BaseEntity;
import Salvation.Clinic.model.entity.drug.Drugs;
import Salvation.Clinic.model.entity.users.Users;
import Salvation.Clinic.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "drugOrderItems")
public class DrugOrderItems extends BaseEntity {

    private String itemName;
    private Double salesPrice;
    private Integer quantity;
    private Double amount;
    private Double purchasePrice;
    private Double purchaseAmount;
    private Double profit;
    private String description;
    private LocalDateTime transactionDate;
    private String status;
    private String drugCategory;
    private String orderBy;
    private String administerTo;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", updatable = true)
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "createdById", insertable = true, updatable = true)
    private Users createdBy;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="drugId", updatable = true)
    private Drugs drugs;

    public DrugOrderItems(){

    }

}
