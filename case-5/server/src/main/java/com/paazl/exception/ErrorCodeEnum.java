package com.paazl.exception;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ErrorCodeEnum {
    OK(0, "OK", "resource.error.success"),
    UNAVAILABLE_ERROR(1, "UNAVAILABLE_ERROR", "resource.error.unavailable"),
    INVALID_PARAMS(2, "INVALID_PARAMS", "resource.error.params.invalid"),
    NOT_FOUND(3, "NOT_FOUND", "resource.error.entity.notfound"),
    FAIL(4, "FAIL", "resource.error.fail");

    private static final Map<Integer, ErrorCodeEnum> lookup = new HashMap<>();
    static {
        for(ErrorCodeEnum e : EnumSet.allOf(ErrorCodeEnum.class))
            lookup.put(e.getErrorCode(), e);
    }

    private int errorCode;
    private String name;
    private String i18nKey;

    ErrorCodeEnum(int errorCode, String name, String i18nKey) {
        this.errorCode = errorCode;
        this.name = name;
        this.i18nKey = i18nKey;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getName() {
        return this.name;
    }

    public String getMessageKey() {
        return i18nKey;
    }

    public String getDefaultMessage() {
        switch (this){
            case OK:
                return "Is success";
            case UNAVAILABLE_ERROR:
                return "An unavailable error has been encountered";
            case INVALID_PARAMS:
                return "Invalid parameters were received";
            case NOT_FOUND:
                return "Requested entity was not found";
            default:
                return "An undefined error has been encountered";
        }
    }

    public static ErrorCodeEnum get(int errorCode) {
        return lookup.get(errorCode);
    }
}