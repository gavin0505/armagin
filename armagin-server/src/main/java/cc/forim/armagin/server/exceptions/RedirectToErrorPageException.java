package cc.forim.armagin.server.exceptions;

/**
 * 短链重定向异常
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/19 13:40
 */
public class RedirectToErrorPageException extends RuntimeException {

    public RedirectToErrorPageException(String message) {
        super(message);
    }

    public RedirectToErrorPageException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedirectToErrorPageException(Throwable cause) {
        super(cause);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}