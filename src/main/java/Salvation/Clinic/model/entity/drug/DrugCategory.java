package Salvation.Clinic.model.entity.drug;


import Salvation.Clinic.model.entity.BaseEntity;
import Salvation.Clinic.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;


@Entity
@EntityListeners(BaseListener.class)
@Data
@AllArgsConstructor
@Table(name = "drugCategory")
public class DrugCategory extends BaseEntity {

    private String name;
    private String description;
    private String postedBy;


    public DrugCategory(){
    }


}
