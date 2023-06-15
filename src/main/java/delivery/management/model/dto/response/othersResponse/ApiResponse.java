package delivery.management.model.dto.response.othersResponse;

import delivery.management.model.dto.BaseDto;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
//@Builder
public class ApiResponse<T, U extends BaseDto> {
    private String message; //Failed/ Success
    private Integer code;
    private T data;

    private Map<String, Object> meta = new HashMap<>();

    public ApiResponse() {
    }

    public Map<String, Object> getMeta() {
        return meta;
    }

    public ApiResponse addMeta(String key, Object value){
        meta.put(key, value);
        return this;
    }
    public ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public ApiResponse(String message, Integer code, T data) {
        this.message = message;
        this.data = data;
        this.code = code;
    }


}