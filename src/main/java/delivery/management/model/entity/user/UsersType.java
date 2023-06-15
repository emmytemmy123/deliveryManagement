package delivery.management.model.entity.user;


import delivery.management.model.entity.BaseEntity;
import delivery.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "usersType")
public class UsersType extends BaseEntity {

    private String name;
    private String description;



    public UsersType(){

    }


}
