package Salvation.Clinic.model.entity.treatment;


import Salvation.Clinic.model.entity.BaseEntity;
import Salvation.Clinic.model.entity.room.Room;
import Salvation.Clinic.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
@EntityListeners(BaseListener.class)
@Table(name = "admission")
public class Admission extends BaseEntity {

    private String name;
    private String treatedBy;
    private String roomNo;
    private Integer noOfNights;
    private Double amount;
    private String status;
    private String description;
    private LocalDateTime admissionDate;
    private LocalDateTime dischargeDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "roomId", updatable = true)
    private Room room;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "treatmentId", updatable = true)
    private Treatment treatment;

    public Admission(){

    }

}
