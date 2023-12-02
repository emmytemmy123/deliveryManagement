package Salvation.Clinic.EndPoints;



public class DrugEndPoints {

    public static final String drug="drug";


    public static final String DRUG_PURCHASE="/purchase";
    public static final String FIND_DRUG_PURCHASE=DRUG_PURCHASE+"/list";
    public static final String ADD_DRUG_PURCHASE= DRUG_PURCHASE+"/add";
    public static final String FIND_DRUG_PURCHASE_BY_ID= DRUG_PURCHASE+"/{id}";
    public static final String UPDATE_DRUG_PURCHASE= DRUG_PURCHASE+"/update/{id}";
    public static final String DELETE_DRUG_PURCHASE= DRUG_PURCHASE+"/delete/{id}";
    public static final String SEARCH_DRUG_PURCHASE_BY_NAME= DRUG_PURCHASE+"/searchProductPurchaseListByName";
    public static final String SEARCH_DRUG_PURCHASE_BY_DATE_RANGE= DRUG_PURCHASE+"/searchProductPurchaseListByDateRange";


    public static final String PRODUCT_FACILITY="/facility";
    public static final String FIND_PRODUCT_FACILITY=PRODUCT_FACILITY+"/list";
    public static final String ADD_PRODUCT_FACILITY= PRODUCT_FACILITY+"/add";
    public static final String FIND_PRODUCT_FACILITY_BY_ID= PRODUCT_FACILITY+"/{id}";
    public static final String UPDATE_PRODUCT_FACILITY= PRODUCT_FACILITY+"/update/{id}";
    public static final String DELETE_PRODUCT_FACILITY= PRODUCT_FACILITY+"/delete/{id}";
    public static final String FIND_PRODUCT_FACILITY_BY_PRODUCT_UUID= PRODUCT_FACILITY+"/searchProductFacilityByProductId";
    public static final String SEARCH_PRODUCT_FACILITY_BY_DATE_RANGE= PRODUCT_FACILITY+"/searchProductPurchaseListByDateRange";



    public static final String DRUG="";
    public static final String FIND_DRUG=DRUG+"/list";
    public static final String ADD_DRUG = DRUG+"/add";
    public static final String FIND_DRUG_BY_ID= DRUG+"/{id}";
    public static final String UPDATE_DRUG= DRUG+"/update/{id}";
    public static final String DELETE_DRUG= DRUG+"/delete/{id}";
    public static final String SEARCH_DRUG_BY_CATEGORY= DRUG+"/searchProductListByCategory";
    public static final String SEARCH_DRUG_BY_NAME= DRUG+"/searchProductListByName";


    public static final String DRUG_CATEGORY="/category";
    public static final String FIND_DRUG_CATEGORY=DRUG_CATEGORY+"/list";
    public static final String ADD_DRUG_CATEGORY= DRUG_CATEGORY+"/add";
    public static final String FIND_DRUG_CATEGORY_BY_ID= DRUG_CATEGORY+"/{id}";
    public static final String UPDATE_DRUG_CATEGORY= DRUG_CATEGORY+"/update/{id}";
    public static final String DELETE_DRUG_CATEGORY= DRUG_CATEGORY+"/delete/{id}";


}
