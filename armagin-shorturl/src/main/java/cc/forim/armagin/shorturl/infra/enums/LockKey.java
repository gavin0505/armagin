package cc.forim.armagin.shorturl.infra.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 分布式锁KEY
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/18 09:39
 */

@RequiredArgsConstructor
@Getter
public enum LockKey {

    /**
     * 创建短链映射场景
     */
    CREATE_URL_MAP("armagin:short_url:map:create", "创建URL映射", 0L, 10000L),

    /**
     * 编辑短链映射场景
     */
    EDIT_URL_MAP("armagin:short_url:map:edit", "修改URL映射", 0L, 10000L),
    ;

    private final String code;
    private final String desc;
    private final long waitTime;
    private final long releaseTime;
}