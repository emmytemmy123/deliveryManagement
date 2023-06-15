package delivery.management.model.entity.transaction;


import delivery.management.model.entity.BaseEntity;
import delivery.management.model.entity.products.ProductItems;
import delivery.management.model.entity.user.Users;
import delivery.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "delivery")
public class Delivery extends BaseEntity {

    private String deliveryNo;
    private Double totalDeliveryAmount;
    private Double totalAmountDue;
    private String deliveryStatus;
    private String deliveryDate;
    private Integer serialNo;
    private String paymentMode;
    private Integer totalQuantity;
    private Integer totalWeight;
    private String postedBy;
    private String deliverBy;
    private String dispatchTo;



    @ManyToOne
    @JoinColumn(name = "usersId", insertable = true, updatable = true)
    private Users users;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<ProductItems> productItemsList;


    public Delivery(){}


}
