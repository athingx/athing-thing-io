package io.github.athingx.athing.thing.io.raspberry.pi;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class LedTestCase {

    private static final Context pi4j = Pi4J.newAutoContext();

    @BeforeClass
    public static void remote() {
        // System.setProperty("pi4j.host", "192.168.31.27");
        // System.setProperty("pi4j.port", "8888");
        // System.setProperty("pi4j.remote", "true");
    }

    @AfterClass
    public static void destroy() {
        pi4j.shutdown();
    }

    @Ignore
    @Test
    public void led() throws InterruptedException {

        final DigitalOutputConfig config = DigitalOutput.newConfigBuilder(pi4j)
                .id("led")
                .name("LED")
                .address(22)
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.LOW)
                .build();

        final DigitalOutput led = pi4j.create(config);
        // led.blink(1, TimeUnit.SECONDS);
        for (int i = 0; i < 10; i++) {
            led.setState(i % 2);
            Thread.sleep(500);
        }

    }

}
