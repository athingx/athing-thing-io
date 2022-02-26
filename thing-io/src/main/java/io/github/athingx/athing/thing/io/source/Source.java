package io.github.athingx.athing.thing.io.source;

import java.nio.channels.ByteChannel;

/**
 * 数据源
 */
public interface Source<T extends ByteChannel> {

    Class<T> getClassType();

    /**
     * 类型
     *
     * @return 类型
     */
    Type getType();

    /**
     * 唯一标识
     *
     * @return 唯一标识
     */
    String getIdentity();

    /**
     * 数据源类型
     */
    enum Type {

        /**
         * 串口
         */
        SERIAL

    }

}
