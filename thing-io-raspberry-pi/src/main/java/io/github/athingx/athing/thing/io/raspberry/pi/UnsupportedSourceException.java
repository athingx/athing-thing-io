package io.github.athingx.athing.thing.io.raspberry.pi;

import io.github.athingx.athing.thing.io.source.Source;

import java.io.IOException;

/**
 * 不支持的数据源类型
 */
public class UnsupportedSourceException extends IOException {

    private final Source<?> source;

    public UnsupportedSourceException(Source<?> source) {
        super("unsupported source-type: %s".formatted(source.getType()));
        this.source = source;
    }

    public Source<?> getSource() {
        return source;
    }

}
