package Salvation.Clinic.model.entity.users;


import Salvation.Clinic.model.entity.BaseUser;
import Salvation.Clinic.model.entity.room.Room;
import Salvation.Clinic.model.entity.treatment.Treatment;
import Salvation.Clinic.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.catalina.User;

import javax.persistence.*;


@Data
@Entity
@AllArgsConstructor
@Table(name = "users")
@EntityListeners(BaseListener.class)
public class Users extends BaseUser {

    private String usersCategory;
    private String designation;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "usersCategoryId", updatable = true)
    private UsersCategory userCategory;



    public Users(){
    }


}
