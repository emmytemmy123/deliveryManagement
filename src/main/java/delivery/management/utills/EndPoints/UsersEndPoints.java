    package delivery.management.utills.EndPoints;



public class UsersEndPoints {

    public static final String users="users";


    public static final String USERS="";
    public static final String FIND_USERS=USERS+"/list";
    public static final String ADD_USERS= USERS+"/add";
    public static final String UPDATE_USERS_USERNAME= USERS+"/update/{username}";
    public static final String FIND_USERS_USERNAME= USERS+"/username/{username}";
    public static final String FIND_USERS_EMAIL= USERS+"/email/{email}";
    public static final String FIND_USERS_BY_ID= USERS+"/get/{uuid}";
    public static final String UPDATE_USERS= USERS+"/update/{uuid}";
    public static final String UPDATE_USERS_PHOTO= USERS+"/updateUsersPhoto/{uuid}";
    public static final String LOGIN_USERS= USERS+"loginUsers";
    public static final String LOGIN_ADMIN= USERS+"loginAdmin";
    public static final String RESET_USERS_PASSWORD= USERS+"/resetPassword";
    public static final String FORGOT_USERS_PASSWORD= USERS+"/forgotEmployeePassword";
    public static final String AUTHENTICATE_USERS= USERS+"authenticates";
    public static final String AUTHENTICATE_ADMIN= USERS+"authenticates/admin";
    public static final String GIVE_ACCESS_TO_USERS= USERS+"/access/{uuid}/{userRole}";


    public static final String USERTYPE="userType";
    public static final String FIND_USERTYPE=USERTYPE+"/list";
    public static final String ADD_USERTYPE= USERTYPE+"/add";
    public static final String FIND_USERTYPE_BY_NAME= USERTYPE+"/name";
    public static final String FIND_USERTYPE_BY_ID= USERTYPE+"/{id}";
    public static final String UPDATE_USERTYPE= USERTYPE+"/update/{id}";
    public static final String DELETE_USERTYPE= USERTYPE+"/delete/{id}";









}
