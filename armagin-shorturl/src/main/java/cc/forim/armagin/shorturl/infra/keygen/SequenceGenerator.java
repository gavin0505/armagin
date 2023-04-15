package cc.forim.armagin.shorturl.infra.keygen;

/**
 * 序列生成器
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/15 23:27
 */
public interface SequenceGenerator {

    /**
     * 生成整型序列
     *
     * @return long
     */
    long generate();
}