package com.workshop.zukerjava.exception;

public interface ApiErrorType<E extends Enum<E>> {

    String getErrorMessage();
    Integer getErrorCode();
}
