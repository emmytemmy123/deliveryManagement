package Salvation.Clinic.EndPoints;


public class TreatmentEndPoints {

    public static final String treatment="treatment";

    public static final String TREATMENT="";
    public static final String FIND_TREATMENT=TREATMENT+"/list";
    public static final String ADD_TREATMENT=TREATMENT+"/add";
    public static final String FIND_TREATMENT_BY_ID=TREATMENT+"/{uuid}";
    public static final String UPDATE_TREATMENT= TREATMENT+"/update/{uuid}";
    public static final String DELETE_TREATMENT= TREATMENT+"/delete/{uuid}";

    public static final String ADMISSION="/admission";
    public static final String FIND_ADMISSION=ADMISSION+"/list";
    public static final String ADD_ADMISSION=ADMISSION+"/add";
    public static final String FIND_ADMISSION_BY_ID=ADMISSION+"/{uuid}";
    public static final String UPDATE_ADMISSION= ADMISSION+"/update/{uuid}";
    public static final String DELETE_ADMISSION= ADMISSION+"/delete/{uuid}";
    public static final String SEARCH_ADMISSION_BY_ROOM= ADMISSION+"/searchSubServiceListByRoom";
    public static final String SEARCH_ADMISSION_BY_NAME= ADMISSION+"/searchSubServiceListByName";
    public static final String SEARCH_ADMISSION_BY_CUSTOMER_AND_ROOM= ADMISSION+"/searchSubServiceListByCustomerAndRoom";








}
