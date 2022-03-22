package io.github.athingx.athing.thing.io.raspberry.pi.channel;

import com.fazecast.jSerialComm.SerialPort;
import io.github.athingx.athing.thing.io.channel.SerialChannel;
import io.github.athingx.athing.thing.io.source.SerialSource;
import io.github.athingx.athing.thing.io.source.Source;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * 串口通道
 */
public class SerialChannelImpl implements SerialChannel {

    private final String _string;
    private final SerialPort serial;

    public SerialChannelImpl(SerialSource source) throws IOException {
        this._string = "%s/channel".formatted(source.getIdentity());
        this.serial = openSerialPort(source);
    }

    private SerialPort openSerialPort(SerialSource source) throws IOException {
        final SerialPort serial = SerialPort.getCommPort(source.getIdentity());

        // 设置波特率
        serial.setBaudRate(source.getBaud());

        // 设置数据位
        switch (source.getDataBits()) {
            case BITS_5 -> serial.setNumDataBits(5);
            case BITS_6 -> serial.setNumDataBits(6);
            case BITS_7 -> serial.setNumDataBits(7);
            case BITS_8 -> serial.setNumDataBits(8);
        }

        // 设置停止位
        switch (source.getStopBits()) {
            case BITS_1 -> serial.setNumStopBits(1);
            case BITS_2 -> serial.setNumStopBits(2);
        }

        // 设置流控
        switch (source.getFlowControl()) {
            case NONE -> serial.setFlowControl(SerialPort.FLOW_CONTROL_DISABLED);
            case HARDWARE -> serial.setFlowControl(SerialPort.FLOW_CONTROL_DISABLED
                    | SerialPort.FLOW_CONTROL_RTS_ENABLED
                    | SerialPort.FLOW_CONTROL_CTS_ENABLED
                    | SerialPort.FLOW_CONTROL_DSR_ENABLED
                    | SerialPort.FLOW_CONTROL_DTR_ENABLED);
            case SOFTWARE -> serial.setFlowControl(SerialPort.FLOW_CONTROL_DISABLED
                    | SerialPort.FLOW_CONTROL_XONXOFF_IN_ENABLED
                    | SerialPort.FLOW_CONTROL_XONXOFF_OUT_ENABLED);
        }

        // 设置校验位
        switch (source.getParity()) {
            case NONE -> serial.setParity(SerialPort.NO_PARITY);
            case EVEN -> serial.setParity(SerialPort.EVEN_PARITY);
            case ODD -> serial.setParity(SerialPort.ODD_PARITY);
        }

        // 打开串口
        if (!serial.openPort()) {
            throw new IOException("%s open failure, code=%d(%d)".formatted(
                    this,
                    serial.getLastErrorCode(),
                    serial.getLastErrorLocation()
            ));
        }

        return serial;
    }

    @Override
    public String toString() {
        return _string;
    }

    @Override
    public int read(ByteBuffer buffer) throws IOException {
        final int length = serial.readBytes(buffer.array(), buffer.remaining(), buffer.arrayOffset());
        if (-1 == length) {
            throw new EOFException();
        }
        buffer.position(buffer.position() + length);
        return length;
    }

    @Override
    public int write(ByteBuffer buffer) throws IOException {
        final int length = serial.writeBytes(buffer.array(), buffer.remaining(), buffer.arrayOffset());
        if (-1 == length) {
            throw new EOFException();
        }
        buffer.position(buffer.position() + length);
        return length;
    }

    @Override
    public boolean isOpen() {
        return serial.isOpen();
    }

    @Override
    public void close() throws IOException {
        if (!serial.closePort()) {
            throw new IOException("%s close failure, code=%d(%d)".formatted(
                    this,
                    serial.getLastErrorCode(),
                    serial.getLastErrorLocation()
            ));
        }
    }

}
