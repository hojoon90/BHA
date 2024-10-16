package com.bupjangsa.common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

import static com.bupjangsa.common.ResponseCodes.RESULT_SUCCESS;

@Getter
@Builder
public final class AppResponse<T> {

    private final int resultCode;
    private final T data;
    private final String resultMsg;

    public static<T> AppResponse<T> responseSuccess(T data){
        return new AppResponse<>(RESULT_SUCCESS.getStatusCode().value(),
                data, RESULT_SUCCESS.getMessage());
    }

    public static AppResponse<Void> responseVoidSuccess(Integer statusCode){
        return new AppResponse<>(statusCode,
                null, RESULT_SUCCESS.getMessage());
    }

    public static<T> AppResponse<T> responseFail(ResponseCodes codes){
        return new AppResponse<>(codes.getStatusCode().value(),
                null, codes.getMessage());
    }

    public static<T> AppResponse<T> responseFailMsg(HttpStatus status , String message){
        return new AppResponse<>(status.value(),
                null, message);
    }

}
