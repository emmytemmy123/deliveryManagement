package delivery.management.model.entity.user;

import delivery.management.model.entity.BaseUser;
import delivery.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

//@MappedSuperclass
@Data
@Entity
@EntityListeners(BaseListener.class)
@Table (name = "appUser")
@AllArgsConstructor
public class AppUser extends BaseUser {

    private String roles;

    public AppUser(){}

   }
