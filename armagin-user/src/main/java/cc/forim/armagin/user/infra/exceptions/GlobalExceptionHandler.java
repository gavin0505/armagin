package cc.forim.armagin.user.infra.exceptions;

import cc.forim.armagin.common.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/4 14:20
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * IllegalArgumentException处理
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResultVo<String> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        log.info(e.getMessage());
        return ResultVo.error(e.getMessage());
    }
}
