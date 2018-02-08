package com.paazl.exception;

public class ClientWSException extends RuntimeException {
    private String debugMessage;
    private Class<?> classError;

    public ClientWSException(String message) {
        super(message);
    }

    public ClientWSException(String message, String debugMessage, Class<?> classError) {
        super(message);
        this.debugMessage = debugMessage;
        this.classError = classError;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public Class<?> getClassError() {
        return classError;
    }

    public void setClassError(Class<?> classError) {
        this.classError = classError;
    }
}
