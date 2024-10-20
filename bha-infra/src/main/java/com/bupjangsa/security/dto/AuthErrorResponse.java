package com.bupjangsa.security.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthErrorResponse<T> {

    private final int resultCode;
    private final String resultMsg;
    private final T data;


}
