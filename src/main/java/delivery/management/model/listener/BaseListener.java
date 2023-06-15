package delivery.management.model.listener;

import delivery.management.model.entity.document.Document;
import delivery.management.model.entity.products.ProductItems;
import delivery.management.model.entity.transaction.Delivery;
import delivery.management.model.entity.user.Users;
import delivery.management.model.entity.transportation.Vehicle;
import delivery.management.model.entity.transportation.VehicleCategory;
import delivery.management.model.entity.user.UsersType;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import java.util.UUID;

@Component
public class BaseListener {

    @PrePersist
    private void beforeCreate(Object data) {

        if(data instanceof Delivery){
            Delivery delivery = (Delivery) data;
            delivery.setUuid(UUID.randomUUID());
        }

        else if(data instanceof Document){
            Document document = (Document) data;
            document.setUuid(UUID.randomUUID());
        }

        else if(data instanceof ProductItems){
            ProductItems productItems = (ProductItems) data;
            productItems.setUuid(UUID.randomUUID());
        }

        else if(data instanceof Users){
            Users users = (Users) data;
            users.setUuid(UUID.randomUUID());
        }

        else if(data instanceof UsersType){
            UsersType usersType = (UsersType) data;
            usersType.setUuid(UUID.randomUUID());
        }

        else if(data instanceof VehicleCategory){
            VehicleCategory vehicleCategory = (VehicleCategory) data;
            vehicleCategory.setUuid(UUID.randomUUID());
        }

        else if(data instanceof Vehicle){
            Vehicle vehicle = (Vehicle) data;
            vehicle.setUuid(UUID.randomUUID());
        }

        else if(data instanceof Users){
            Users users = (Users) data;
            users.setUuid(UUID.randomUUID());
        }





    }








}
