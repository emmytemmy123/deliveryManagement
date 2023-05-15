package delivery.management.model.entity.user;


import delivery.management.model.entity.BaseUser;
import delivery.management.model.entity.transportation.Vehicle;
import delivery.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "driver")
public class Driver extends BaseUser {

    private String nin;
    private String driverLicence;


    @OneToOne
    @JoinColumn(name = "vehicleId", insertable = true, updatable = true)
    private Vehicle vehicle;


    public Driver(){}


    }
