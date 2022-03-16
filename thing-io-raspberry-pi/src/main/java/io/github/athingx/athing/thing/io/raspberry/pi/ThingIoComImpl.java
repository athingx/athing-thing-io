package io.github.athingx.athing.thing.io.raspberry.pi;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import io.github.athingx.athing.standard.thing.Thing;
import io.github.athingx.athing.standard.thing.ThingLifeCycle;
import io.github.athingx.athing.thing.io.ThingIoCom;
import io.github.athingx.athing.thing.io.raspberry.pi.channel.SerialChannelImpl;
import io.github.athingx.athing.thing.io.source.Source;
import io.github.athingx.athing.thing.io.source.SerialSource;

import java.io.IOException;
import java.nio.channels.ByteChannel;

public class ThingIoComImpl implements ThingIoCom, ThingLifeCycle {

    private final Context pi4j = Pi4J.newAutoContext();

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ByteChannel> T openChannel(Source<T> source) throws IOException {
        final ByteChannel channel = _openChannel(source);
        assert source.getClassType().isInstance(channel);
        return (T) channel;
    }

    private ByteChannel _openChannel(Source<?> _source) throws IOException {

        // 打开串口数据通道
        if (_source instanceof SerialSource source) {
            return new SerialChannelImpl(pi4j, source);
        }

        // 其他数据源类型暂不支持
        throw new UnsupportedSourceException(_source);

    }

    @Override
    public void onLoaded(Thing thing) {
        thing.getDestroyFuture().onDone(future -> pi4j.shutdown());
    }

}
