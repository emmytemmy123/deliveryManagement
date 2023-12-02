package Salvation.Clinic.model.entity.treatment;


import Salvation.Clinic.model.entity.BaseEntity;
import Salvation.Clinic.model.entity.drug.Drugs;
import Salvation.Clinic.model.entity.transaction.Orders;
import Salvation.Clinic.model.entity.users.Users;
import Salvation.Clinic.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.Order;
import java.util.List;


@EntityListeners(BaseListener.class)
@Entity
@Table(name = "treatment")
@AllArgsConstructor
@Data
public class Treatment extends BaseEntity {

    private String patientName;
    private String treatedBy;
    private String sickness;
    private String status;
    private String drugs;
    private String Description;
    private Double treatmentAmount;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "admissionId", updatable = true)
    private Admission admission;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn( name= "usersId", updatable = true  )
    private Users users;



    public Treatment(){

    }


}
