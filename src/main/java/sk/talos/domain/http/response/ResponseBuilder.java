package sk.talos.domain.http.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {

    public static ResponseEntity<CommonResponseData> success(Object data, HttpStatus status) {
        CommonResponseData response = new CommonResponseData(data);
        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<CommonResponseData> success(Object data, HttpStatus status, ResponseMetaData meta) {
        CommonResponseData response = new CommonResponseData(data, meta);
        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<CommonResponseData> error(String errorMessage, HttpStatus status) {
        ResponseErrorData error = new ResponseErrorData(errorMessage, status.getReasonPhrase());
        CommonResponseData response = new CommonResponseData(null, error);
        return new ResponseEntity<>(response, status);
    }
}
