package delivery.management.model.entity.transportation;


import delivery.management.model.entity.BaseEntity;
import delivery.management.model.entity.user.Users;
import delivery.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "VehicleCategory")
public class VehicleCategory extends BaseEntity {

    private String name;
    private String description;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "createdById", updatable = true)
    private Users createdBy;


    public VehicleCategory(){}


}
