package Salvation.Clinic.EndPoints;



public class RoomEndPoints {

    public static final String room="Room";

    public static final String ROOM="";
    public static final String FIND_ROOM=ROOM+"/list";
    public static final String ADD_ROOM=ROOM+"/add";
    public static final String FIND_ROOM_BY_ID=ROOM+"/{id}";
    public static final String UPDATE_ROOM= ROOM+"/update/{id}";
    public static final String DELETE_ROOM= ROOM+"/delete/{id}";

    public static final String ROOM_CATEGORY="/category";
    public static final String FIND_ROOM_CATEGORY=ROOM_CATEGORY+"/list";
    public static final String ADD_ROOM_CATEGORY= ROOM_CATEGORY+"/add";
    public static final String FIND_ROOM_CATEGORY_BY_ID= ROOM_CATEGORY+"/{id}";
    public static final String UPDATE_ROOM_CATEGORY= ROOM_CATEGORY+"/update/{id}";
    public static final String DELETE_ROOM_CATEGORY= ROOM_CATEGORY+"/delete/{id}";
    public static final String SEARCH_SUB_SERVICE_BY_ROOM_ID= ROOM_CATEGORY+"/searchSub_serviceListByRoomId";
    public static final String SEARCH_ROOM_FACILITY_BY_ROOM_ID= ROOM_CATEGORY+"/searchRoomFacilityListByRoomId";


    public static final String ROOM_FACILITY="/facility";
    public static final String FIND_ROOM_FACILITY=ROOM_FACILITY+"/list";
    public static final String ADD_ROOM_FACILITY= ROOM_FACILITY+"/add";
    public static final String FIND_ROOM_FACILITY_BY_ID= ROOM_FACILITY+"/{id}";
    public static final String UPDATE_ROOM_FACILITY= ROOM_FACILITY+"/update/{id}";
    public static final String DELETE_ROOM_FACILITY= ROOM_FACILITY+"/delete/{id}";
    public static final String SEARCH_ROOM_FACILITY_BY_ROOM_NUMBER = ROOM_FACILITY+"/searchRoomFacilityListByRoomNumber";
    public static final String SEARCH_ROOM_FACILITY_BY_NAME = ROOM_FACILITY+"/searchRoomFacilityListByName";



}
