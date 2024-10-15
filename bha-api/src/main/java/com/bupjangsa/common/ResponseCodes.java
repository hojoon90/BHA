package com.bupjangsa.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResponseCodes {
    //SuccessCode
    //200
    RESULT_SUCCESS(HttpStatus.OK,"OK"),

    //ErrorCode
    //400
    WRONG_PARAMETER(HttpStatus.BAD_REQUEST, "잘못된 파라미터 입니다."),
    NEGATIVE_NUMBER(HttpStatus.BAD_REQUEST, "송금액은 음수가 될 수 없습니다."),
    QUOTE_EXPIRED(HttpStatus.BAD_REQUEST, "견적서가 만료 되었습니다."),
    LIMIT_EXCESS(HttpStatus.BAD_REQUEST, "오늘 송금 한도 초과 입니다."),

    //401
    UNAUTHORIZED_TOKEN(HttpStatus.UNAUTHORIZED, "사용할 수 없는 토큰입니다."),

    //500
    UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 에러 입니다."),

    //503
    EXTERNAL_API_ERROR(HttpStatus.SERVICE_UNAVAILABLE, "외부 API 오류입니다. ");

    private final HttpStatus statusCode;
    private final String message;

    public String getMessage(Object... arg) {
        return String.format(message, arg);
    }
}

