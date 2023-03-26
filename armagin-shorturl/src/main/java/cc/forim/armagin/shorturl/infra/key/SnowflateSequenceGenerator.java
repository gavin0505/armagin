package cc.forim.armagin.shorturl.infra.key;

import lombok.RequiredArgsConstructor;

/**
 * 雪花算法序列生成器
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/26 22:37
 */
@RequiredArgsConstructor
public class SnowflateSequenceGenerator implements SequenceGenerator {

    private final long workerId;
    private final long dataCenterId;

    private JavaSnowflake javaSnowflake;

    public void init() {
        this.javaSnowflake = new JavaSnowflake(workerId, dataCenterId);
    }

    @Override
    public long generate() {
        return javaSnowflake.nextId();
    }
}