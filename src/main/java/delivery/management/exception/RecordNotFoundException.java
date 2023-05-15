package delivery.management.exception;

public class RecordNotFoundException extends RuntimeException{

    private String message;

    public RecordNotFoundException(String message){
        super(message);
        this.message=message;
    }
    public RecordNotFoundException(){

    }

}
