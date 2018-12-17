package com.workshop.zukerjava.exception;

public enum GeneralErrorType implements ApiErrorType<GeneralErrorType>{

    ERROR_DESCRIPTION(9999, "Something went wrong.");

    private Integer errorCode;
    private String errorMessage;

    GeneralErrorType(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public Integer getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
