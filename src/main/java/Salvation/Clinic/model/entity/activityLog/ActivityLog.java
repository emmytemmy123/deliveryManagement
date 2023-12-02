package Salvation.Clinic.model.entity.activityLog;


import Salvation.Clinic.model.entity.BaseEntity;
import Salvation.Clinic.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "activityLog")
public class ActivityLog extends BaseEntity {

    private String category;
    private String name;
    private String description;
    private String performedBy;
    private LocalDateTime performedDate;
    private Double amountPaid;




    public ActivityLog(){}


}
