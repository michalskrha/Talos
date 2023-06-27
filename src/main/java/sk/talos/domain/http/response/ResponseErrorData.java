package sk.talos.domain.http.response;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResponseErrorData {

    private String message;
    private String code;
    private HttpStatus status;
    private String uuid;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timestamp;

    private List<ObjectError> errors = new ArrayList<>();

    public ResponseErrorData() {
    }

    public ResponseErrorData(String message, String code) {
        this.message = message;
        this.code = code;
        this.timestamp = LocalDateTime.now();
    }


    public ResponseErrorData(HttpStatus status, String message, List<ObjectError> errors) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.errors = errors;
    }

    public ResponseErrorData(HttpStatus status, String message, String uuid, List<ObjectError> errors) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.errors = errors;
        this.uuid = uuid;
    }

    public ResponseErrorData(HttpStatus status, String message, ObjectError error) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.errors.add(error);
    }

}
