package com.bupjangsa.common;

import lombok.Builder;
import lombok.Getter;

import static com.bupjangsa.common.ResponseCodes.RESULT_SUCCESS;

@Getter
@Builder
public final class CommonResponse<T> {

    private final int resultCode;
    private final T data;
    private final String resultMsg;

    public static<T> CommonResponse responseSuccess(T data){
        return CommonResponse.builder()
                .resultCode(RESULT_SUCCESS.getStatusCode().value())
                .data(data)
                .resultMsg(RESULT_SUCCESS.getMessage())
                .build();
    }

    public static CommonResponse responseFail(ResponseCodes codes){
        return CommonResponse.builder()
                .resultCode(codes.getStatusCode().value())
                .data(null)
                .resultMsg(codes.getMessage())
                .build();
    }

}
