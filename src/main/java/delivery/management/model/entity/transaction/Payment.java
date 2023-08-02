package delivery.management.model.entity.transaction;


import delivery.management.model.entity.BaseEntity;
import delivery.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "payment")
public class Payment extends BaseEntity {

    private String paidBy;
    private Double amountPaid;
    private String paymentMode;
    private String status;
    private  String paidTo;
    @CreationTimestamp
    private LocalDateTime paymentDate;
    private String deliveryNo;



    public Payment(){

    }


}
