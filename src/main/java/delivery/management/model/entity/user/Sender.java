package delivery.management.model.entity.user;


import delivery.management.model.entity.BaseEntity;
import delivery.management.model.entity.BaseUser;
import delivery.management.model.entity.transaction.Delivery;
import delivery.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "sender")
public class Sender extends BaseUser {


   private String nin;


    public Sender (){
    }

}
