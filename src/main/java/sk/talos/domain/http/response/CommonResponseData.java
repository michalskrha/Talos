package sk.talos.domain.http.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponseData<T> {

    private T data;
    private ResponseErrorData error;

    public CommonResponseData() {
    }

    public CommonResponseData(T data) {
        this.data = data;
    }

    public CommonResponseData(T data, ResponseErrorData error) {
        this.data = data;
        this.error = error;
    }

}
