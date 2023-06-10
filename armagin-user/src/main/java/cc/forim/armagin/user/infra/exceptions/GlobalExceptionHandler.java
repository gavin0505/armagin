package cc.forim.armagin.user.infra.exceptions;

import cc.forim.armagin.common.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultVo<Map<String, String>> missingServletRequestParameterExceptionHandler(HttpServletRequest req, MethodArgumentNotValidException ex) {

        //处理后返回错误结果
        BindingResult bindingResult = ex.getBindingResult();
        Map<String, String> errorMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach((fieldError) -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        // 包装 ResultVO 结果
        return ResultVo.error("500","参数校验错误",
                errorMap);
    }

    /**
     * IllegalArgumentException处理
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResultVo<String> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        log.info(e.getMessage());
        return ResultVo.error(e.getMessage());
    }
}
