package io.github.athingx.athing.thing.io.raspberry.pi;

import io.github.athingx.athing.standard.thing.Thing;
import io.github.athingx.athing.standard.thing.ThingLifeCycle;
import io.github.athingx.athing.standard.thing.boot.ThInject;
import io.github.athingx.athing.thing.io.ThingIoCom;
import io.github.athingx.athing.thing.io.raspberry.pi.channel.SerialChannelImpl;
import io.github.athingx.athing.thing.io.source.SerialSource;
import io.github.athingx.athing.thing.io.source.Source;

import java.io.IOException;
import java.nio.channels.ByteChannel;

class ThingIoComImpl implements ThingIoCom, ThingLifeCycle {

    @ThInject
    private Thing thing;

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ByteChannel> T openChannel(Source<T> source) throws IOException {
        return (T) _openChannel(source);
    }

    private ByteChannel _openChannel(Source<?> _source) throws IOException {

        // 打开串口数据通道
        if (_source instanceof SerialSource source) {
            return new SerialChannelImpl(thing, source);
        }

        // 其他数据源类型暂不支持
        throw new UnsupportedSourceException(_source);

    }

}
