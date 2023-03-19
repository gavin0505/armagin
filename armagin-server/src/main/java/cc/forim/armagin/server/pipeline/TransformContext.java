package cc.forim.armagin.server.pipeline;

import cc.forim.armagin.server.enums.TransformStatus;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;
import java.util.Objects;

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
     * 短链转换状态
     */
    private TransformStatus transformStatus;

    /**
     * 重定向任务
     */
    final ThreadLocal<Runnable> redirectAction = new TransmittableThreadLocal<>();


    /**
     * 设置参数
     */
    public void setParam(String key, Object value) {
        this.params.put(key, value);
    }

    /**
     * 获取参数
     */
    @SuppressWarnings("unchecked")
    public <T> T getParam(String key) {
        return (T) params.get(key);
    }

    /**
     * 设置请求头
     */
    public void setHeader(String key, String value) {
        headers.put(key, value);
    }

    /**
     * 获取请求头的值
     */
    public String getHeader(String key) {
        return headers.get(key);
    }

    public void setRedirectAction(Runnable redirectAction) {
        this.redirectAction.set(redirectAction);
    }

    public Runnable getRedirectAction() {
        Runnable redirectAction = this.redirectAction.get();
        return Objects.nonNull(redirectAction) ? redirectAction : () -> {
        };
    }

    /**
     * 责任链中断的标识
     */
    private Boolean needBreak;

}