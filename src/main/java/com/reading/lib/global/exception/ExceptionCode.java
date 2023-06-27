package com.reading.lib.global.exception;

import lombok.Getter;

public enum ExceptionCode {
    COMMENT_ALREADY_EXISTS(400, "Comment already exists"),
    CANNOT_UPDATE_COMMENT(404, "Cannot update comment");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
