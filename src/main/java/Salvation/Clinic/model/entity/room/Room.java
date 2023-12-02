package Salvation.Clinic.model.entity.room;


import Salvation.Clinic.model.entity.BaseEntity;
import Salvation.Clinic.model.entity.users.Users;
import Salvation.Clinic.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "room")
@EntityListeners(BaseListener.class)
@AllArgsConstructor
public class Room extends BaseEntity {

    private String roomType;
    private String roomNo;
    private Integer noOfBed;
    private String status;
    private Double cost;
    private String createdBy;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "roomCategoryId", updatable = true)
    private RoomCategory roomCategory;




    public Room(){

    }



}
