package cc.forim.armagin.common.exception;

/**
 * 锁异常类
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/15 23:08
 */
public class LockException extends RuntimeException {

    public LockException(String message) {
        super(message);
    }

    public LockException(String message, Throwable cause) {
        super(message, cause);
    }

    public LockException(Throwable cause) {
        super(cause);
    }
}