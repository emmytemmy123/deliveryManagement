package delivery.management.model.entity.transaction;

import delivery.management.model.entity.BaseEntity;
import delivery.management.model.entity.products.ProductItems;
import delivery.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Table(name = "dispatchDriver")
@Entity
public class DispatchDriver extends BaseEntity {

    private String sender;
    private String dispatchName;
    private String receiverAddress;
    private String receiverName;
    private Double totalAmount;
    @CreationTimestamp
    private LocalDateTime DispatchDate;
    private String deliveryNo;
    private String deliveryId;
    private String email;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<ProductItems> productList;

    @OneToMany(mappedBy = "dispatchDriver", cascade = CascadeType.ALL)
    private List<Delivery> deliveryList;



    public DispatchDriver(){

    }


}
