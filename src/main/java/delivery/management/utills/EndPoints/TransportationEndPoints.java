package delivery.management.utills.EndPoints;




public class TransportationEndPoints {

    public static final String transportation="transportation";


    public static final String VEHICLE_CATEGORY="/vehicleCategory";
    public static final String FIND_VEHICLE_CATEGORY=VEHICLE_CATEGORY+"/list";
    public static final String ADD_VEHICLE_CATEGORY= VEHICLE_CATEGORY+"/add";
    public static final String FIND_VEHICLE_CATEGORY_BY_ID= VEHICLE_CATEGORY+"/{id}";
    public static final String UPDATE_VEHICLE_CATEGORY= VEHICLE_CATEGORY+"/update/{id}";
    public static final String DELETE_VEHICLE_CATEGORY= VEHICLE_CATEGORY+"/delete/{id}";
    public static final String FIND_LIST_OF_VEHICLE_CATEGORY_BY_DATE_RANGE= VEHICLE_CATEGORY+"/findVehicleCategoryByDateRange";
    public static final String FIND_LISTS_OF_VEHICLE_CATEGORY_BY_DATE= VEHICLE_CATEGORY+"/findVehicleCategoryByDate";


    public static final String VEHICLE="/category";
    public static final String FIND_VEHICLE=VEHICLE+"/list";
    public static final String ADD_VEHICLE= VEHICLE+"/add";
    public static final String FIND_VEHICLE_BY_ID= VEHICLE+"/{id}";
    public static final String UPDATE_VEHICLE= VEHICLE+"/update/{id}";
    public static final String DELETE_VEHICLE= VEHICLE+"/delete/{id}";
    public static final String FIND_LIST_OF_VEHICLE_BY_DATE_RANGE= VEHICLE+"/findVehicleByDateRange";
    public static final String FIND_LISTS_OF_VEHICLE_BY_DATE= VEHICLE+"/findVehicleByDate";



}
