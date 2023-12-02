package Salvation.Clinic.model.listener;

import Salvation.Clinic.model.entity.activityLog.ActivityLog;
import Salvation.Clinic.model.entity.drug.DrugCategory;
import Salvation.Clinic.model.entity.drug.DrugPurchase;
import Salvation.Clinic.model.entity.drug.Drugs;
import Salvation.Clinic.model.entity.room.Room;
import Salvation.Clinic.model.entity.room.RoomCategory;
import Salvation.Clinic.model.entity.transaction.DrugOrderItems;
import Salvation.Clinic.model.entity.transaction.Orders;
import Salvation.Clinic.model.entity.transaction.Payment;
import Salvation.Clinic.model.entity.treatment.Admission;
import Salvation.Clinic.model.entity.treatment.Treatment;
import Salvation.Clinic.model.entity.users.Users;
import Salvation.Clinic.model.entity.users.UsersCategory;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import java.util.UUID;

@Component
public class BaseListener {

    @PrePersist
    private void beforeCreate(Object data) {


        if (data instanceof UsersCategory) {
            UsersCategory usersCategory = (UsersCategory) data;
            usersCategory.setUuid(UUID.randomUUID());
        }

        else if (data instanceof Users) {
            Users users = (Users) data;
            users.setUuid(UUID.randomUUID());
        }

        else if (data instanceof ActivityLog) {
            ActivityLog activityLog = (ActivityLog) data;
            activityLog.setUuid(UUID.randomUUID());
        }

        else if (data instanceof DrugCategory) {
            DrugCategory drugCategory = (DrugCategory) data;
            drugCategory.setUuid(UUID.randomUUID());
        }

        else if (data instanceof DrugPurchase) {
            DrugPurchase drugPurchase = (DrugPurchase) data;
            drugPurchase.setUuid(UUID.randomUUID());
        }

        else if (data instanceof Drugs) {
            Drugs drugs = (Drugs) data;
            drugs.setUuid(UUID.randomUUID());
        }

        else if (data instanceof Room) {
            Room room = (Room) data;
            room.setUuid(UUID.randomUUID());
        }

        else if (data instanceof DrugOrderItems) {
            DrugOrderItems drugOrderItems = (DrugOrderItems) data;
            drugOrderItems.setUuid(UUID.randomUUID());
        }

        else if (data instanceof Payment) {
            Payment payment = (Payment) data;
            payment.setUuid(UUID.randomUUID());
        }

        else if (data instanceof Admission) {
            Admission admission = (Admission) data;
            admission.setUuid(UUID.randomUUID());
        }

        else if (data instanceof Treatment) {
            Treatment treatment = (Treatment) data;
            treatment.setUuid(UUID.randomUUID());
        }

        else if (data instanceof RoomCategory) {
            RoomCategory roomCategory = (RoomCategory) data;
            roomCategory.setUuid(UUID.randomUUID());
        }
        else if (data instanceof Orders) {
            Orders orders = (Orders) data;
            orders.setUuid(UUID.randomUUID());
        }



    }




    }









