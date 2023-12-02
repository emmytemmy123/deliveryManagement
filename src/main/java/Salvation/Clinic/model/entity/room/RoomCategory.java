package Salvation.Clinic.model.entity.room;


import Salvation.Clinic.model.entity.BaseEntity;
import Salvation.Clinic.model.entity.users.Users;
import Salvation.Clinic.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roomCategory")
@EntityListeners(BaseListener.class)
@AllArgsConstructor
public class RoomCategory extends BaseEntity {

    private String name;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "userId", updatable = true)
    private Users users;

    public RoomCategory(){

    }

}



