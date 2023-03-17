package cc.forim.armagin.server.pipeline;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * 责任链上下文
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/16 19:59
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class TransformContext {
    /**
     * 标识责任链的code
     */
    private String code;

    /**
     * 短链压缩码
     */
    private String compressionCode;

    /**
     * 短链请求头
     */
    private Map<String, String> headers = Maps.newHashMap();

    /**
     * 短链参数
     */
    private Map<String, Object> params = Maps.newHashMap();

    /**
     * 设置参数
     */
    public void setParam(String key, Object value) {
        this.params.put(key, value);
    }

    /**
     * 设置请求头
     */
    public void setHeader(String key, String value) {
        headers.put(key, value);
    }


    /**
     * 责任链中断的标识
     */
    private Boolean needBreak;

    /**
     * 流程处理的结果
     */
    Runnable redirection;
}