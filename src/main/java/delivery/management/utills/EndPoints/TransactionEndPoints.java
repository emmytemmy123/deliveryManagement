package delivery.management.utills.EndPoints;


public class TransactionEndPoints {

    public static final String transaction="transaction";


    public static final String PAYMENT="/payment";
    public static final String FIND_PAYMENT=PAYMENT+"/list";
    public static final String ADD_PAYMENT= PAYMENT+"/add";
//  public static final String FIND_PAYMENT_BY_ID= PAYMENT+"/{id}";
    public static final String FIND_PAYMENT_BY_ORDER_ID = PAYMENT+"/findPaymentByOrderId";
    public static final String UPDATE_PAYMENT= PAYMENT+"/update/{id}";
    public static final String DELETE_PAYMENT= PAYMENT+"/delete/{id}";
    public static final String FIND_LIST_OF_PAYMENT_BY_DATE_RANGE= PAYMENT+"/findPaymentByDateRange";
    public static final String FIND_LISTS_OF_PAYMENT_BY_DATE= PAYMENT+"/findPaymentByDate";
    public static final String FIND_PAYMENT_BY_SALES_PERSON= PAYMENT+"/findPaymentBySalesPerson";
    public static final String FIND_PAYMENT_BY_CUSTOMER= PAYMENT+"/findPaymentByCustomer";
    public static final String FIND_PAYMENT_BY_DELIVERY_NO= PAYMENT+"/deliveryNo";



    public static final String ORDER_ITEMS="/orderItems";
    public static final String FIND_ORDER_ITEMS=ORDER_ITEMS+"/list";
//    public static final String ADD_ORDER_ITEMS= ORDER_ITEMS+"/add";
    public static final String FIND_ORDER_ITEMS_BY_ID= ORDER_ITEMS+"/{id}";
//    public static final String UPDATE_ORDER_ITEMS= ORDER_ITEMS+"/update/{id}";
//    public static final String DELETE_ORDER_ITEMS= ORDER_ITEMS+"/delete/{id}";

    public static final String DELIVERY="/delivery";
    public static final String FIND_DELIVERY=DELIVERY+"/list";
    public static final String ADD_DELIVERY= DELIVERY+"/add";
    public static final String FIND_DELIVERY_BY_ID= DELIVERY+"/{id}";
    public static final String UPDATE_DELIVERY= DELIVERY+"/update/{id}";
    public static final String DELETE_DELIVERY= DELIVERY+"/delete/{id}";
    public static final String FIND_DELIVERY_BY_SENDER= DELIVERY+"/findDeliveryBySender";
    public static final String FIND_DELIVERY_BY_DELIVERY_NO= DELIVERY+"/deliveryNo/{deliveryNo}";
    public static final String FIND_LISTS_OF_DELIVERY_BY_DATE= DELIVERY+"/findOrderByDate";


    public static final String DISPATCH="/dispatch";
    public static final String FIND_DISPATCH=DISPATCH+"/list";
    public static final String ADD_DISPATCH= DISPATCH+"/add";
    public static final String FIND_DISPATCH_BY_SENDER= DISPATCH+"/findDispatchBySender";
    public static final String FIND_DISPATCH_BY_DELIVERY_NO= DISPATCH+"/deliveryNo";
    public static final String FIND_DISPATCH_BY_DISPATCH_NAME= DISPATCH+"/dispatchName";
    public static final String FIND_DISPATCH_BY_EMAIL= DISPATCH+"/email/{email}";









}
