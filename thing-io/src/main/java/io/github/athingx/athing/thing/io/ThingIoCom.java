package io.github.athingx.athing.thing.io;

import io.github.athingx.athing.standard.component.ThingCom;
import io.github.athingx.athing.thing.io.source.Source;

import java.io.IOException;
import java.nio.channels.ByteChannel;

public interface ThingIoCom extends ThingCom {

    /**
     * 打开数据通道
     *
     * @param source 数据源
     * @return 数据通道
     * @throws IOException 打开失败
     */
    <T extends ByteChannel> T openChannel(Source<T> source) throws IOException;

}
