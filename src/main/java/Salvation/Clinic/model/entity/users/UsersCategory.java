package Salvation.Clinic.model.entity.users;


import Salvation.Clinic.model.entity.BaseEntity;
import Salvation.Clinic.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "usersCategory")
@EntityListeners(BaseListener.class)
@AllArgsConstructor
public class UsersCategory extends BaseEntity {

    private String name;
    private String description;

    @OneToMany(mappedBy = "usersCategory", cascade = CascadeType.ALL )
    private List<Users> users;


    public UsersCategory(){

    }

}
