package io.github.athingx.athing.thing.io.source;

import java.nio.channels.ByteChannel;

/**
 * 基础数据源
 */
public abstract class BaseSource<T extends ByteChannel> implements Source<T> {

    private final Class<T> classType;
    private final Type type;

    protected BaseSource(Type type, Class<T> classType) {
        this.type = type;
        this.classType = classType;
    }

    @Override
    public Class<T> getClassType() {
        return classType;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return getIdentity();
    }

}
