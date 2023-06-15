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


    public static final String USERS="";
    public static final String FIND_USERS=USERS+"/list";
    public static final String ADD_USERS= USERS+"/add";
    public static final String FIND_USERS_BY_ID= USERS+"/get/{id}";
    public static final String UPDATE_USERS= USERS+"/update/{id}";
    public static final String DELETE_USERS= USERS+"/delete/{id}";
    public static final String LOGIN_USERS= USERS+"loginUsers";
    public static final String RESET_USERS_PASSWORD= USERS+"/resetPassword";
    public static final String FORGOT_USERS_PASSWORD= USERS+"/forgotEmployeePassword";



    public static final String USER="appUser";
    public static final String FIND_USER=USER+"/list";
    public static final String ADD_USER= USER+"/add";
    public static final String FIND_USER_BY_ID= USER+"/{id}";
    public static final String UPDATE_USER= USER+"/update/{id}";
    public static final String DELETE_USER= USER+"/delete/{id}";
    public static final String RESET_USER_PASSWORD= USER+"/resetPassword";
    public static final String FORGOT_USER_PASSWORD= USER+"/forgotUserPassword";
    public static final String LOGIN_USER= USER+"/loginUser";
    public static final String AUTHENTICATE_USER= USER+"authenticate";

    public static final String AUTHENTICATE_USERS= USER+"authenticates";
    public static final String AUTHENTICATE_USERES= USER+"authenticating";


    public static final String GIVE_ACCESS_TO_USER= USER+"/access/{uuid}/{userRole}";


    public static final String USERTYPE="userType";
    public static final String FIND_USERTYPE=USERTYPE+"/list";
    public static final String ADD_USERTYPE= USERTYPE+"/add";
    public static final String FIND_USERTYPE_BY_NAME= USERTYPE+"/name";
    public static final String FIND_USERTYPE_BY_ID= USERTYPE+"/{id}";
    public static final String UPDATE_USERTYPE= USERTYPE+"/update/{id}";
    public static final String DELETE_USERTYPE= USERTYPE+"/delete/{id}";




    public static final String IMAGE="/image";
    public static final String FIND_IMAGE=IMAGE+"/list";
    public static final String ADD_IMAGE= IMAGE+"/add";
    public static final String FIND_IMAGE_BY_ID= IMAGE+"/{id}";
    public static final String UPDATE_IMAGE= IMAGE+"/update/{id}";
    public static final String DELETE_IMAGE= IMAGE+"/delete/{id}";






}
