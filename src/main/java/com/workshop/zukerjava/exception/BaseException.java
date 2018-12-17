package com.workshop.zukerjava.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected HttpStatus httpStatus;
    protected String errorMessage;
    protected Integer errorCode;


    protected BaseException(Exception ex) {
        super(ex);
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorMessage = GeneralErrorType.ERROR_DESCRIPTION.getErrorMessage();
        this.errorCode = GeneralErrorType.ERROR_DESCRIPTION.getErrorCode();
    }

    protected BaseException(Exception ex, String errorMessage) {
        super(ex);
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorMessage = errorMessage;
        this.errorCode = GeneralErrorType.ERROR_DESCRIPTION.getErrorCode();
    }

    protected BaseException(Exception ex, HttpStatus httpStatus) {
        super(ex);
        this.httpStatus = httpStatus;
        this.errorMessage = GeneralErrorType.ERROR_DESCRIPTION.getErrorMessage();
        this.errorCode = GeneralErrorType.ERROR_DESCRIPTION.getErrorCode();
    }

    protected BaseException(Exception ex, Integer errorCode) {
        super(ex);
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorMessage = GeneralErrorType.ERROR_DESCRIPTION.getErrorMessage();
        this.errorCode = errorCode;
    }

    protected BaseException(Exception ex, String errorMessage, Integer errorCode) {
        super(ex);
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    protected BaseException(Exception ex, HttpStatus httpStatus, String errorMessage) {
        super(ex);
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
        this.errorCode = GeneralErrorType.ERROR_DESCRIPTION.getErrorCode();
    }

    protected BaseException(Exception ex, HttpStatus httpStatus, String errorMessage, Integer errorCode) {
        super(ex);
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    protected BaseException(ApiErrorType<? extends Enum<?>> apiErrorType, HttpStatus httpStatus) {
        super(apiErrorType.getErrorMessage());
        this.httpStatus = httpStatus;
        this.errorMessage = apiErrorType.getErrorMessage();
        this.errorCode = apiErrorType.getErrorCode();
    }

    protected BaseException(ApiErrorType<? extends Enum<?>> apiErrorType, String errorMessage) {
        super(apiErrorType.getErrorMessage());
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorMessage = errorMessage;
        this.errorCode = apiErrorType.getErrorCode();
    }

//    protected BaseException (ApiErrorType<? extends Enum<?>> apiErrorType, String message, HttpStatus httpStatus) {
//        super(message);
//        this.httpStatus = httpStatus;
//        this.errorMessage = getCustomMessage(apiErrorType, message);
//        this.errorCode = apiErrorType.getErrorCode();
//    }

    protected BaseException(ApiErrorType<? extends Enum<?>> apiErrorType, String paramsList, HttpStatus httpStatus) {
        super(ErrorMessage.getErrorMessage(apiErrorType, paramsList));
        this.httpStatus = httpStatus;
        this.errorMessage = ErrorMessage.getErrorMessage(apiErrorType, paramsList);
        this.errorCode = apiErrorType.getErrorCode();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

}
