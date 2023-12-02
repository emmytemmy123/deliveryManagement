package Salvation.Clinic.model.entity.transaction;


import Salvation.Clinic.model.entity.BaseEntity;
import Salvation.Clinic.model.entity.treatment.Treatment;
import Salvation.Clinic.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@EntityListeners(BaseListener.class)
@Entity
@AllArgsConstructor
@Table(name = "payment")
public class Payment extends BaseEntity {

    private Double amountPaid;
    private String description;
    private String paymentMode;
    private String paymentStatus;
    private String paidBy;
    private String paymentRefNo;
    private Double totalAmount;
    private Double balance;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", updatable = true)
    private Orders orders;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "treatmentId", updatable = true)
    private Treatment treatment;


    public Payment(){
    }


}
