package delivery.management.model.entity.user;


import delivery.management.model.entity.BaseUser;
import delivery.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "users")
public class Users extends BaseUser {

   private String driverLicense;
   private String usersCategory;
   private String roles;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "usersTypeId", updatable = true)
    private UsersType usersType;

    public Users(){
    }


}
