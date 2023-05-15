package delivery.management.model.listener;

import delivery.management.model.entity.document.Document;
import delivery.management.model.entity.products.ProductItems;
import delivery.management.model.entity.transaction.Delivery;
import delivery.management.model.entity.transaction.Payment;
import delivery.management.model.entity.user.AppUser;
import delivery.management.model.entity.user.Customer;
import delivery.management.model.entity.user.Driver;
import delivery.management.model.entity.user.Sender;
import delivery.management.model.entity.transportation.Vehicle;
import delivery.management.model.entity.transportation.VehicleCategory;
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

        else if(data instanceof Payment){
            Payment payment = (Payment) data;
            payment.setUuid(UUID.randomUUID());
        }

        else if(data instanceof Customer){
            Customer customer = (Customer) data;
            customer.setUuid(UUID.randomUUID());
        }

        else if(data instanceof Driver){
            Driver driver = (Driver) data;
            driver.setUuid(UUID.randomUUID());
        }

        else if(data instanceof AppUser){
            AppUser user = (AppUser) data;
            user.setUuid(UUID.randomUUID());
        }

        else if(data instanceof VehicleCategory){
            VehicleCategory vehicleCategory = (VehicleCategory) data;
            vehicleCategory.setUuid(UUID.randomUUID());
        }

        else if(data instanceof Vehicle){
            Vehicle vehicle = (Vehicle) data;
            vehicle.setUuid(UUID.randomUUID());
        }

        else if(data instanceof Sender){
            Sender sender = (Sender) data;
            sender.setUuid(UUID.randomUUID());
        }





    }








}
