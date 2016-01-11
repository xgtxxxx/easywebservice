package xgt.easy.webservice.exception;

public class EasyWebserviceException extends RuntimeException{
    public EasyWebserviceException() {
    }

    public EasyWebserviceException(String message) {
        super(message);
    }

    public EasyWebserviceException(String message, Throwable cause) {
        super(message, cause);
    }

    public EasyWebserviceException(Throwable cause) {
        super(cause);
    }

    public EasyWebserviceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
