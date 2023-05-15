package delivery.management.model.entity.transportation;


import delivery.management.model.entity.BaseEntity;
import delivery.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;


@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "vehicle")
public class Vehicle extends BaseEntity {

    private String brand;
    private String model;
    private String colour;
    private String plateNo;
    private String driver;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicleCategoryId", updatable = true)
    private VehicleCategory vehicleCategory;




    public Vehicle(){}

}
