package Salvation.Clinic.model.entity.transaction;


import Salvation.Clinic.model.entity.BaseEntity;
import Salvation.Clinic.model.entity.drug.Drugs;
import Salvation.Clinic.model.entity.treatment.Treatment;
import Salvation.Clinic.model.entity.users.Users;
import Salvation.Clinic.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "orders")
public class Orders extends BaseEntity {

    private String orderNo;
    private String orderBy;
    private String administerDrugTo;
    private Double drugAmount;
    private String drugStatus;
    private Integer serialNo;
    private String roomNo;
    private String treatedBy;
    private Integer totalQuantity;
    private Double treatmentAmount;
    private Double admissionAmount;
    private Double grandTotal;
    private Double amountPaid;
    private Double amountDue;



    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<DrugOrderItems> drugOrderItemsList;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "paymentId", updatable = true)
    private Payment payment;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "orderById", updatable = true)
    private Users users;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "drugId", updatable = true)
    private Drugs drugs;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "treatmentId", updatable = true)
    private Treatment treatment;

    public Orders(){}



}
