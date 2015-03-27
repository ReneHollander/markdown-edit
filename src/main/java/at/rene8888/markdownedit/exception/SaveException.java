package at.rene8888.markdownedit.exception;

import java.io.IOException;

public class SaveException extends IOException {

    public SaveException() {
        super();
    }

    public SaveException(String message) {
        super(message);
    }

    public SaveException(String message, IOException cause) {
        super(message, cause);
    }

    public SaveException(Throwable cause) {
        super(cause);
    }

}
