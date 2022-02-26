package io.github.athingx.athing.thing.io.raspberry.pi;

import io.github.athingx.athing.standard.component.ThingCom;
import io.github.athingx.athing.standard.thing.boot.ThingBoot;
import io.github.athingx.athing.standard.thing.boot.ThingBootArgument;
import org.kohsuke.MetaInfServices;

import java.io.InputStream;
import java.util.Properties;

@MetaInfServices
public class ThingIoBoot implements ThingBoot {

    @Override
    public ThingCom[] boot(String productId, String thingId, ThingBootArgument argument) {
        return new ThingCom[]{
                new ThingIoComImpl()
        };
    }

    @Override
    public Properties getProperties() {
        final Properties prop = ThingBoot.super.getProperties();
        try (final InputStream in = ThingIoBoot.class.getResourceAsStream("/io/github/athingx/athing/thing/io/raspberry/pi/thing-boot.properties")) {
            if (null != in) {
                prop.load(in);
            }
        } catch (Exception cause) {
            // ignore...
        }
        return prop;
    }

}
