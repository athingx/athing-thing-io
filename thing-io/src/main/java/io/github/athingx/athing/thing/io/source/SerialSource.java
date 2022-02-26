package io.github.athingx.athing.thing.io.source;

import io.github.athingx.athing.thing.io.channel.SerialChannel;

import static io.github.athingx.athing.thing.io.source.Source.Type.SERIAL;

/**
 * 串口数据源（RS-232）
 */
public class SerialSource extends BaseSource<SerialChannel> {

    private final String identity;
    private final String _string;
    private final String device;
    private final int baud;
    private final DataBits dataBits;
    private final StopBits stopBits;
    private final Parity parity;
    private final FlowControl flowControl;

    /**
     * 串口数据源（RS-232）
     *
     * @param device      设备号
     * @param baud        波特率
     * @param dataBits    数据位
     * @param stopBits    停止位
     * @param parity      奇偶校验位
     * @param flowControl 流控策略
     */
    public SerialSource(String device, int baud, DataBits dataBits, StopBits stopBits, Parity parity, FlowControl flowControl) {
        super(SERIAL, SerialChannel.class);
        this.device = device;
        this.baud = baud;
        this.dataBits = dataBits;
        this.stopBits = stopBits;
        this.parity = parity;
        this.flowControl = flowControl;
        this.identity = String.format("%s://%s", SERIAL.name().toLowerCase(), device);
        this._string = String.format("%s?baud=%s&data=%s&stop=%s&parity=%s&flow-control=%s", getIdentity(), baud, dataBits, stopBits, parity, flowControl);
    }

    /**
     * 构建N81协议串口数据源
     *
     * @param device 设备号
     * @param baud   波特率
     * @return n81协议串口
     */
    public static SerialSource n81(String device, int baud) {
        return new SerialSource(device, baud, DataBits.BITS_8, StopBits.BITS_1, Parity.NONE, FlowControl.NONE);
    }


    @Override
    public String getIdentity() {
        return identity;
    }

    @Override
    public String toString() {
        return _string;
    }

    /**
     * 获取设备号
     *
     * @return 设备号
     */
    public String getDevice() {
        return device;
    }

    /**
     * 获取波特率
     *
     * @return 波特率
     */
    public int getBaud() {
        return baud;
    }

    /**
     * 获取数据位
     *
     * @return 数据位
     */
    public DataBits getDataBits() {
        return dataBits;
    }

    /**
     * 获取停止位
     *
     * @return 停止位
     */
    public StopBits getStopBits() {
        return stopBits;
    }

    /**
     * 获取奇偶校验类型
     *
     * @return 奇偶校验类型
     */
    public Parity getParity() {
        return parity;
    }

    /**
     * 获取流控策略
     *
     * @return 流控策略
     */
    public FlowControl getFlowControl() {
        return flowControl;
    }

    /**
     * 停止位
     *
     * <p>用于表示单个包的最后一位。</p>
     *
     * <p>
     * 由于数据是在传输线上定时的，并且每一个设备有其自己的时钟，很可能在通信中两台设备间出现了小小的不同步。
     * 因此停止位不仅仅是表示传输的结束，并且提供计算机校正 时钟同步的机会。
     * </p>
     *
     * <p>适用于停止位的位数越多，不同时钟同步的容忍程度越大，但是数据传输率同时也越慢。</p>
     */
    public enum StopBits {
        BITS_1,
        BITS_2
    }

    /**
     * 数据位
     *
     * <p>通信中实际数据位数</p>
     *
     * <p>
     * 当计算机发送一个信息包，实际的数据不会是8位的，标准的值是5、7和8位。标准的ASCII码是0～127（7位）。扩展的ASCII码是0～255（8位）。
     * </p>
     */
    public enum DataBits {
        BITS_5,
        BITS_6,
        BITS_7,
        BITS_8
    }

    /**
     * 奇偶校验类型
     */
    public enum Parity {

        /**
         * 无校验
         */
        NONE,

        /**
         * 偶校验
         */
        ODD,

        /**
         * 奇校验
         */
        EVEN

    }

    /**
     * 流控策略
     */
    public enum FlowControl {

        /**
         * 不流控
         */
        NONE,

        /**
         * 硬件流控
         */
        HARDWARE,

        /**
         * 软件流控
         */
        SOFTWARE

    }

}
