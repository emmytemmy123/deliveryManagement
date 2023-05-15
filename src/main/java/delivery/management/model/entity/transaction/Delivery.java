package delivery.management.model.entity.transaction;


import delivery.management.model.entity.BaseEntity;
import delivery.management.model.entity.products.ProductItems;
import delivery.management.model.entity.user.Customer;
import delivery.management.model.entity.user.Driver;
import delivery.management.model.entity.user.Sender;
import delivery.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private LocalDateTime deliveryDate;
    private Integer serialNo;
    private String paymentMode;
    private Integer totalQuantity;
    private String postedBy;
    private String deliverBy;
    private String dispatchTo;



    @ManyToOne
    @JoinColumn(name = "driverId", insertable = true, updatable = true)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "senderId", insertable = true, updatable = true)
    private Sender sender;

    @ManyToOne
    @JoinColumn(name = "customerId", insertable = true, updatable = true)
    private Customer customer;


    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<ProductItems> productItemsList;


    public Delivery(){}


}
