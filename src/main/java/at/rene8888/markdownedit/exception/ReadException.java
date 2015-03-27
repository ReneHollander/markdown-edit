package at.rene8888.markdownedit.exception;

import java.io.IOException;

public class ReadException extends IOException {

    public ReadException() {
        super();
    }

    public ReadException(String message) {
        super(message);
    }

    public ReadException(String message, IOException cause) {
        super(message, cause);
    }

    public ReadException(Throwable cause) {
        super(cause);
    }

}
