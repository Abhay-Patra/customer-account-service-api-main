package com.hsbc.api.customer.account.util;

import com.hsbc.api.customer.account.exception.ErrorResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class ApplicationUtil {
    public static ErrorResponse populateConstraintViolationException() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Bad Request");
        errorResponse.setCode("400");
        return errorResponse;
    }

    public static ErrorResponse notFound() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Not Found");
        errorResponse.setCode("405");
        return errorResponse;
    }

    public static ErrorResponse unsupportedMediaType() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Unsupported Media Type");
        errorResponse.setCode("415");
        return errorResponse;
    }

    public static ErrorResponse populateMethodNotAllowedException() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Method Not Allowed");
        errorResponse.setCode("404");
        return errorResponse;
    }

    public static ErrorResponse handleHttpMessageNotReadable() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Not Acceptable");
        errorResponse.setCode("406");
        return errorResponse;
    }
}
