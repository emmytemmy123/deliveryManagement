    package delivery.management.utills.EndPoints;



public class UsersEndPoints {

    public static final String users="users";

    public static final String CUSTOMER="/customer";
    public static final String FIND_CUSTOMER=CUSTOMER+"/list";
    public static final String ADD_CUSTOMER=CUSTOMER+"/add";
    public static final String FIND_CUSTOMER_BY_ID=CUSTOMER+"/{id}";
    public static final String UPDATE_CUSTOMER= CUSTOMER+"/update/{id}";
    public static final String DELETE_CUSTOMER= CUSTOMER+"/customerDelete/{id}";
    public static final String LOGIN_CUSTOMER= CUSTOMER+"loginCustomer";
    public static final String RESET_CUSTOMER_PASSWORD= CUSTOMER+"/resetPassword";
    public static final String FORGOT_CUSTOMER_PASSWORD= CUSTOMER+"/forgotCustomerPassword";


    public static final String SENDER="/sender";
    public static final String FIND_SENDER=SENDER+"/list";
    public static final String ADD_SENDER= SENDER+"/add";
    public static final String FIND_SENDER_BY_ID= SENDER+"/{id}";
    public static final String UPDATE_SENDER= SENDER+"/update/{id}";
    public static final String DELETE_SENDER= SENDER+"/delete/{id}";
    public static final String LOGIN_SENDER= SENDER+"loginEmployee";
    public static final String RESET_SENDER_PASSWORD= SENDER+"/resetPassword";
    public static final String FORGOT_SENDER_PASSWORD= SENDER+"/forgotEmployeePassword";


    public static final String DRIVER="/driver";
    public static final String FIND_DRIVER=DRIVER+"/list";
    public static final String ADD_DRIVER= DRIVER+"/add";
    public static final String FIND_DRIVER_BY_ID= DRIVER+"/{id}";
    public static final String UPDATE_DRIVER= DRIVER+"/update/{id}";
    public static final String DELETE_DRIVER= DRIVER+"/delete/{id}";
    public static final String LOGIN_DRIVER= DRIVER+"loginEmployee";
    public static final String RESET_DRIVER_PASSWORD= DRIVER+"/resetPassword";
    public static final String FORGOT_DRIVER_PASSWORD= DRIVER+"/forgotEmployeePassword";


    public static final String USER="";
    public static final String FIND_USER=USER+"/list";
    public static final String ADD_USER= USER+"/add";
    public static final String FIND_USER_BY_ID= USER+"/{id}";
    public static final String UPDATE_USER= USER+"/update/{id}";
    public static final String DELETE_USER= USER+"/delete/{id}";
    public static final String RESET_USER_PASSWORD= USER+"/resetPassword";
    public static final String FORGOT_USER_PASSWORD= USER+"/forgotUserPassword";
    public static final String LOGIN_USER= USER+"/loginUser";
    public static final String AUTHENTICATE_USER= USER+"authenticate";
    public static final String GIVE_ACCESS_TO_USER= USER+"/access/{uuid}/{userRole}";


    public static final String IMAGE="/image";
    public static final String FIND_IMAGE=IMAGE+"/list";
    public static final String ADD_IMAGE= IMAGE+"/add";
    public static final String FIND_IMAGE_BY_ID= IMAGE+"/{id}";
    public static final String UPDATE_IMAGE= IMAGE+"/update/{id}";
    public static final String DELETE_IMAGE= IMAGE+"/delete/{id}";






}
