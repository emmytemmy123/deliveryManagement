package delivery.management.model.entity.products;

import delivery.management.model.entity.BaseEntity;
import delivery.management.model.entity.transaction.Delivery;
import delivery.management.model.entity.transaction.DispatchDriver;
import delivery.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "product")
public class ProductItems extends BaseEntity {

    private String productName;
    private Integer quantity;
    private String model;
    private String colour;
    private Integer weight;
    private String photo;
    private String status;
    private Double amount;
    private String description;



    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "deliveryId", insertable = true, updatable = true)
    private Delivery delivery;


//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "dispatchId", insertable = true, updatable = true)
//    private DispatchDriver dispatch;



    public ProductItems(){}

}
