package delivery.management.model.dto.enums;

public enum DeliveryCost {

    DOOR_DELIVERY("door delivery"),
    PICKUP_STATION("pick up station");

    public final String label;

    DeliveryCost(String label){
        this.label = label;
    }

}
