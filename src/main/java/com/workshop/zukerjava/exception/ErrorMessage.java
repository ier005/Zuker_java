package com.workshop.zukerjava.exception;

import java.text.MessageFormat;

public interface ErrorMessage {

    static String getErrorMessage(ApiErrorType apiErrorType, String paramsList) {

        String params[] = paramsList.split("\\|");
        MessageFormat mf = new MessageFormat(apiErrorType.getErrorMessage());
        String errorMessage = mf.format(params);
        return errorMessage;
    }
}
