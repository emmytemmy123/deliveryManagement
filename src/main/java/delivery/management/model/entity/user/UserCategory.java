package delivery.management.model.entity.user;


import delivery.management.model.entity.BaseEntity;
import delivery.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "userCategory")
public class UserCategory extends BaseEntity {

    private String name;
    private String description;



    public UserCategory(){

    }


}
