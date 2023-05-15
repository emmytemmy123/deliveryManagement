package delivery.management.model.entity.transaction;


import delivery.management.model.entity.BaseEntity;
import delivery.management.model.entity.user.Customer;
import delivery.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "payment")
public class Payment extends BaseEntity {

    private Double amount;
    private Double balance;
    private String paymentMode;
    private String paymentStatus;
    private String paidBy;
    private String tranReference;
    private Integer serialNo;


    @ManyToOne
    @JoinColumn(name = "deliveryId", insertable = true, updatable = true)
    private Delivery delivery;

    public Payment(){}


    }
