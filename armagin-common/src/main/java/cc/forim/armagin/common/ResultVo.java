package cc.forim.armagin.common;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 统一返回处理
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/17 23:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "统一返回处理")
public class ResultVo<T> implements Serializable {

    private static final String CODE_SUCCESS = "200";

    private static final String CODE_ERROR = "500";

    private String code;

    private String message;

    private T data;

    public static <T> ResultVo<T> success(T data) {
        ResultVo<T> result = new ResultVo<>();
        result.code = CODE_SUCCESS;
        result.data = data;
        result.message = "成功";
        return result;
    }

    public static <T> ResultVo<T> success(String message, T data) {
        ResultVo<T> result = new ResultVo<>();
        result.code = CODE_SUCCESS;
        result.data = data;
        result.message = message;
        return result;
    }

    public static <T> ResultVo<T> success(String code, String message, T data) {
        ResultVo<T> result = new ResultVo<>();
        result.code = code;
        result.message = message;
        result.data = data;
        return result;
    }

    public static <T> ResultVo<T> error(String code, String message) {
        ResultVo<T> result = new ResultVo<>();
        result.code = code;
        result.message = message;
        return result;
    }

    public static <T> ResultVo<T> error(String code, String message, T data) {
        ResultVo<T> result = new ResultVo<>();
        result.code = code;
        result.message = message;
        result.data = data;
        return result;
    }

    public static <T> ResultVo<T> error(String message) {
        ResultVo<T> result = new ResultVo<>();
        result.code = CODE_ERROR;
        result.message = message;
        return result;
    }

    public static <T> ResultVo<T> error(ResultVo<T> resultVO) {
        return error(resultVO.getCode(), resultVO.getMessage());
    }
}