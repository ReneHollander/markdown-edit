package com.markdownedit.app.exception;

public class FXMLLoadException extends RuntimeException {

    public FXMLLoadException() {
        super();
    }

    public FXMLLoadException(String message) {
        super(message);
    }

    public FXMLLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FXMLLoadException(Throwable cause) {
        super(cause);
    }

}
