package io.github.athingx.athing.thing.io.raspberry.pi.channel;

import com.pi4j.context.Context;
import com.pi4j.io.serial.*;
import io.github.athingx.athing.thing.io.channel.SerialChannel;
import io.github.athingx.athing.thing.io.source.SerialSource;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * 串口通道
 */
public class SerialChannelImpl implements SerialChannel {

    private final String _string;
    private final Serial serial;

    public SerialChannelImpl(Context pi4j, SerialSource source) {
        this.serial = pi4j.create(config(pi4j, source));
        this._string = "%s/channel".formatted(source.getIdentity());
    }

    // PI4J串口配置
    private SerialConfig config(Context pi4j, SerialSource source) {

        // builder for serial-config
        final SerialConfigBuilder builder = SerialConfigBuilder.newInstance(pi4j)
                .baud(source.getBaud())
                .device(source.getDevice())
                .name(source.getIdentity())
                .description(source.toString());

        // 映射数据位
        switch (source.getDataBits()) {
            case BITS_5 -> builder.dataBits(5);
            case BITS_6 -> builder.dataBits(6);
            case BITS_7 -> builder.dataBits(7);
            case BITS_8 -> builder.dataBits(8);
        }

        // 映射停止位
        switch (source.getStopBits()) {
            case BITS_1 -> builder.stopBits(1);
            case BITS_2 -> builder.stopBits(2);
        }

        // 映射校验位
        switch (source.getParity()) {
            case ODD -> builder.parity(Parity.ODD);
            case EVEN -> builder.parity(Parity.EVEN);
            case NONE -> builder.parity(Parity.NONE);
        }

        // 映射流控策略
        switch (source.getFlowControl()) {
            case HARDWARE -> builder.flowControl(FlowControl.HARDWARE);
            case SOFTWARE -> builder.flowControl(FlowControl.SOFTWARE);
            case NONE -> builder.flowControl(FlowControl.NONE);
        }

        return builder.build();
    }

    @Override
    public String toString() {
        return _string;
    }

    /*
     * 检查：读/写长度不小于0，否则为I/O错误
     */
    private int checkLength(String action, int length) throws IOException {
        if (length == -1) {
            throw new EOFException("%s %s: EOF".formatted(this, action));
        } else if (length < 0) {
            throw new IOException("%s %s: %d".formatted(this, action, length));
        }
        return length;
    }

    @Override
    public int read(ByteBuffer buffer) throws IOException {
        return checkLength("read", serial.read(buffer));
    }

    @Override
    public int write(ByteBuffer buffer) throws IOException {
        return checkLength("write", serial.write(buffer));
    }

    @Override
    public boolean isOpen() {
        return serial.isOpen();
    }

    @Override
    public void close() throws IOException {
        try {
            serial.close();
        } catch (Exception cause) {
            throw new IOException("%s close error!".formatted(this), cause);
        }
    }

}
