package com.project.refreshments.exception;

public enum RefreshmentsCardError {

    USER_ALREADY_EXISTS_ERROR(400);

    private int code;

    RefreshmentsCardError(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
